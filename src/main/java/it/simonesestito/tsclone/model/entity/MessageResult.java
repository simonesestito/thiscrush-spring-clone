package it.simonesestito.tsclone.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MessageResult {
    private String username;
    private List<Data> messages;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {
        private Long id;
        private String text;
        private String addresseeUsername;
        private Long date;
        private boolean secret;
    }
}
