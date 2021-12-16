package com.example.subscriptionspring.model;

import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;


@Data
public class BellModel {

    public String subscriptionId;
    public List<Data> data;

    @lombok.Data
    public final static class Data {
        private String id;
        private String type;
        private Ring_info ring_info;

        @lombok.Data
        public final static class Ring_info {
            private String type;
            private String value;
            private Metadata metadata;

            @lombok.Data
            public final static class Metadata {
                private TimeInstant timeInstant;

                @lombok.Data
                public final static class TimeInstant {
                    private String type;
                    private String value;
                }
            }
        }
    }


}
