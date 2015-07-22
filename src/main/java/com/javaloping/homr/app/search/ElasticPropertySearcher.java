package com.javaloping.homr.app.search;

import com.javaloping.homr.app.model.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchPhrasePrefixQuery;

/**
 * @author victormiranda@gmail.com
 */
public class ElasticPropertySearcher implements PropertySearcher {

    private final Client client;
    private final ElasticConfig elasticConfig;

    private static final Logger LOG = LogManager.getLogger(ElasticPropertySearcher.class.getName());

    public ElasticPropertySearcher(final ElasticConfig elasticConfig) {
        this.elasticConfig = elasticConfig;

        client = getClient();

        if (!isIndexExist()) {
            createIndex();
        }
    }

    private boolean isIndexExist() {
        return client.admin().indices()
                .prepareExists(elasticConfig.getIndexName())
                .execute()
                .actionGet()
                .isExists();
    }

    @Override
    public PropertyDocument index(Property property) throws IOException {
        final PropertyDocument propertyDocument = getDocument(property);

        final String idString = String.valueOf(propertyDocument.getId());

        client.prepareIndex(elasticConfig.getIndexName(), elasticConfig.getObjectName(), idString)
            .setSource(propertyDocument.toJson())
            .execute()
            .actionGet();

        return propertyDocument;
    }

    @Override
    public Object fetch(final int propertyId) {
        final BoolQueryBuilder qb = QueryBuilders.boolQuery()
                .should(matchPhrasePrefixQuery("id", propertyId));

        final SearchResponse search =
                client.prepareSearch(elasticConfig.getIndexName()).setTypes(elasticConfig.getObjectName())
                .setSearchType(SearchType.QUERY_THEN_FETCH)
                .setQuery(qb)
                .execute().actionGet();

        SearchHit hit = search.getHits().getAt(0);

        return PropertyDocument.fromHit(hit);
    }

    private PropertyDocument getDocument(final Property property) {
        final PropertyDocument document = new PropertyDocument();

        document.setId(property.getId());
        document.setName(property.getName());
        document.setBedrooms(property.getFeatures().getBedrooms());
        document.setBathroom(property.getFeatures().getBathrooms());
        document.setFloor(property.getFeatures().getFloor());
        document.setSqMeters(property.getFeatures().getSqMeters());
        document.setPrice(property.getPrice().doubleValue());

        return document;
    }

    @Override
    public List<PropertyDocument> search(PropertyDocument searchDocument) {
        return new ArrayList<PropertyDocument>();
    }

    @Override
    public void refresh() {
        client.admin().indices().prepareRefresh().execute().actionGet();
    }

    private Client getClient() {
        final Client elasticClient;

        if (!elasticConfig.isStandalone()) {
            elasticClient = NodeBuilder.nodeBuilder().local(true).node().client();
        } else {
            elasticClient = new TransportClient()
                    .addTransportAddress(new InetSocketTransportAddress(elasticConfig.getHost(), elasticConfig.getPort()));
        }

        return elasticClient;
    }

    private void createIndex() {
        LOG.debug("Creating index " + elasticConfig.getIndexName());

        CreateIndexRequestBuilder createIndexRequestBuilder =
                client.admin().indices().prepareCreate(elasticConfig.getIndexName());

        createIndexRequestBuilder.execute().actionGet();
    }
}
