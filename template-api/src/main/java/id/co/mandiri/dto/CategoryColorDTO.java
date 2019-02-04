package id.co.mandiri.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

public class CategoryColorDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryColorRequestNewDTO{
        @NonNull
        private String name;
        private String code;
        private String description;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CategoryColorRequestUpdateDTO{
        @NotNull
        private String id;
        @NotNull
        private String name;
        private String code;
        private String description;
    }

}
