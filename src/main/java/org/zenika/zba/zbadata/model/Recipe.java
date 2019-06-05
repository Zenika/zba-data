package org.zenika.zba.zbadata.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.lang.NonNull;
import io.swagger.annotations.Api;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Api(description = "Object Recipe used to transfer data between the Rest API and the database")
@Entity
public class Recipe implements Serializable {

    private long id;
    private String name;
    private String ingredientType;
    private String malt;
    private String creator;
    private List<Step> steps;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(unique = true)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    @NonNull
    public String getMalt() {
        return malt;
    }

    public void setMalt(String malt) {
        this.malt = malt;
    }

    @NonNull
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> step) {
        this.steps = step;
    }

    @JsonCreator
    public static Recipe Create(String jsonString) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Recipe module = null;
        module = mapper.readValue(jsonString, Recipe.class);
        return module;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredientType='" + ingredientType + '\'' +
                ", malt='" + malt + '\'' +
                ", creator='" + creator + '\'' +
                ", step=" + steps +
                '}';
    }
}
