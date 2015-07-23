package com.javaloping.homr.app.search;

import com.javaloping.homr.app.model.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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

    public ElasticPropertySearcher(final ElasticConfig config) {
        elasticConfig = config;

        client = getClient();

        //on dev phase, it will change to a property
        if (isIndexExist()) {
            deleteIndex();
        }

        createIndex();
    }

    private void deleteIndex() {
        if (isIndexExist()) {
            LOG.debug("deleting index " + elasticConfig.getIndexName());
            client.admin().indices().delete(new DeleteIndexRequest(elasticConfig.getIndexName())).actionGet();
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
        final PropertyDocument propertyDocument = PropertyDocument.fromProperty(property);

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

        return PropertyDocument.fromSource(hit.getSource());
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
        return new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress(elasticConfig.getHost(), elasticConfig.getPort()));
    }

    private void createIndex() {
        LOG.debug("Creating index " + elasticConfig.getIndexName());

        CreateIndexRequestBuilder createIndexRequestBuilder =
                client.admin().indices().prepareCreate(elasticConfig.getIndexName());

        createIndexRequestBuilder.execute().actionGet();
    }
}
