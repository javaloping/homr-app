package com.javaloping.homr.app.search;

import com.javaloping.homr.app.model.Property;
import com.javaloping.homr.app.type.PropertyModeType;
import com.javaloping.homr.app.type.PropertyType;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Property Documents to be indexed.
 * They are flattened.
 *
 * @author victormiranda@gmail.com
 */
public class PropertyDocument implements IndexDocument {

    private Integer id;
    private PropertyModeType mode;
    private String name;
    private Boolean lift;
    private Integer floor;
    private int bedrooms;
    private int bathrooms;
    private Float sqMeters;
    private Integer areaId;
    private Float longitude;
    private Float latitude;
    private Double price;
    private Double rentPrice;
    private PropertyType type;
    private Date publishedDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PropertyModeType getMode() {
        return mode;
    }

    public void setMode(PropertyModeType mode) {
        this.mode = mode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getLift() {
        return lift;
    }

    public void setLift(Boolean lift) {
        this.lift = lift;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Float getSqMeters() {
        return sqMeters;
    }

    public void setSqMeters(Float sqMeters) {
        this.sqMeters = sqMeters;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(Double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public PropertyType getType() {
        return type;
    }

    public void setType(PropertyType type) {
        this.type = type;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public XContentBuilder toJson() throws IOException {
        return jsonBuilder().startObject()
                .field("id", getId())
                .field("name", getName())
                .field("mode", getMode())
                .field("lift", getLift())
                .field("floor", getFloor())
                .field("bedrooms", getBedrooms())
                .field("bathrooms", getBathrooms())
                .field("sqMeters", getSqMeters())
                .field("areaId", getAreaId())
                .field("longitude", getLongitude())
                .field("latitude", getLatitude())
                .field("price", getPrice())
                .field("rentPrice", getRentPrice())
                .field("type", getType())
                .field("publishedDate", getPublishedDate())
                .endObject();
    }

    public static PropertyDocument fromProperty(final Property property) {
        final PropertyDocument document = new PropertyDocument();

        document.setId(property.getId());
        document.setName(property.getName());
        document.setLift(property.getFeatures().getLift());
        document.setBedrooms(property.getFeatures().getBedrooms());
        document.setBathrooms(property.getFeatures().getBathrooms());
        document.setFloor(property.getFeatures().getFloor());
        document.setSqMeters(property.getFeatures().getSqMeters());
        document.setPublishedDate(property.getPublishedDate());

        if (property.getPrice() != null) {
            document.setPrice(property.getPrice().doubleValue());
        }

        return document;
    }


    public static PropertyDocument fromSource(Map<String, Object> source) {
        final PropertyDocument document = new PropertyDocument();

        document.setId((Integer) source.get("id"));

        document.setName(IndexDocument.getStringProperty(source, "name"));
        document.setFloor(IndexDocument.getIntegerProperty(source, "floor"));
        document.setLift(IndexDocument.getBooleanProperty(source, "lift"));
        document.setBedrooms(IndexDocument.getIntegerProperty(source, "bedrooms"));
        document.setBathrooms(IndexDocument.getIntegerProperty(source, "bathrooms"));
        document.setSqMeters(IndexDocument.getFloatProperty(source, "sqMeters"));

        document.setPublishedDate(IndexDocument.getDateProperty(source, "publishedDate"));

        document.setAreaId(IndexDocument.getIntegerProperty(source, "areaId"));

        document.setLongitude(IndexDocument.getFloatProperty(source, "longitude"));
        document.setLatitude(IndexDocument.getFloatProperty(source, "latitude"));

        document.setPrice(IndexDocument.getDoubleProperty(source, "price"));
        document.setRentPrice(IndexDocument.getDoubleProperty(source, "rentPrice"));

        document.setType(PropertyType.byName((String) source.get("type")));

        document.setMode(PropertyModeType.byName((String) source.get("mode")));

        return document;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PropertyDocument document = (PropertyDocument) o;

        return new org.apache.commons.lang3.builder.EqualsBuilder()
                .append(bedrooms, document.bedrooms)
                .append(bathrooms, document.bathrooms)
                .append(id, document.id)
                .append(mode, document.mode)
                .append(name, document.name)
                .append(lift, document.lift)
                .append(floor, document.floor)
                .append(sqMeters, document.sqMeters)
                .append(areaId, document.areaId)
                .append(longitude, document.longitude)
                .append(latitude, document.latitude)
                .append(price, document.price)
                .append(rentPrice, document.rentPrice)
                .append(type, document.type)
                .append(publishedDate, document.publishedDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(id)
                .append(mode)
                .append(name)
                .append(lift)
                .append(floor)
                .append(bedrooms)
                .append(bathrooms)
                .append(sqMeters)
                .append(areaId)
                .append(longitude)
                .append(latitude)
                .append(price)
                .append(rentPrice)
                .append(type)
                .append(publishedDate)
                .toHashCode();
    }
}
