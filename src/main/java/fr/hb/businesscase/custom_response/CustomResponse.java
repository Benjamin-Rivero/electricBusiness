package fr.hb.businesscase.custom_response;

import com.fasterxml.jackson.annotation.JsonView;
import fr.hb.businesscase.json_views.JsonViews;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CustomResponse<T> {

    @JsonView(JsonViews.Response.class)
    private int status;

    @JsonView(JsonViews.Response.class)
    private Object value;

    private Class<?> entity;

    @JsonView(JsonViews.Response.class)
    private String getEntityName(){
        return List.of(entity.getTypeName().split("\\.")).getLast();
    }

}
