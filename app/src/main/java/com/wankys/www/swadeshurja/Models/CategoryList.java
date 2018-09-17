package com.wankys.www.swadeshurja.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Elakiya on 5/14/2018.
 */

public class CategoryList {
    @SerializedName("category_id")
    private String category_id;
    @SerializedName("category_name")
    private String category_name;
    @SerializedName("parent_category")
    private String parent_category;
    @SerializedName("scategory_id")
    private String scategory_id;
    @SerializedName("category_logo")
    private String category_logo;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategoryName() {
        return category_name;
    }

    public void setCategoryName(String name) {
        this.category_name = name;
    }

    public String getParent_Category() {
        return parent_category;
    }

    public void setParent_Category(String parent_Category) {
        this.parent_category = parent_Category;
    }

    public String getScategory_id() {
        return scategory_id;
    }

    public void setScategory_id(String scategory_id) {
        this.scategory_id = scategory_id;
    }

    public CategoryList(String category_id, String name, String parent_Category, String scategory_id,String categorylogo) {
        this.category_id = category_id;
        this.category_name = name;
        this.parent_category = parent_Category;
        this.scategory_id = scategory_id;
        this.category_logo = categorylogo;
    }

    public String getCategory_logo() {
        return category_logo;
    }

    public void setCategory_logo(String category_logo) {
        this.category_logo = category_logo;
    }
}
