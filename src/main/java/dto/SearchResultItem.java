package dto;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SearchResultItem {

    private String imageSRC;
    private String imgId;
    private String categoryName;
    private String description;
    private String price;

    public SearchResultItem() {
    }

    public SearchResultItem(String imageSRC, String imgId, String categoryName, String description, String price) {
        this.imageSRC = imageSRC;
        this.imgId = imgId;
        this.categoryName = categoryName;
        this.description = description;
        this.price = price;
    }

    public String getImageSRC() {
        return imageSRC;
    }

    public void setImageSRC(String imageSRC) {
        this.imageSRC = imageSRC;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof SearchResultItem)) {
            return false;
        }

        SearchResultItem item = (SearchResultItem) o;

        return item.imageSRC.equals(imageSRC)&&
                item.imgId.equals(imgId)&&
                categoryName.contains(item.categoryName)&&
                item.description.contains(description)&&
                item.price.equals(price);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 47)
                .append(imageSRC)
                .append(imgId)
                .append(categoryName)
                .append(description)
                .append(price)
                .toHashCode();
    }
}
