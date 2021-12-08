package com.example.subscriptionspring;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


@Data
public class BellModel {

    public String subscriptionId;
    public List<Data> data;

    public BellModel(String subscriptionId, List<Data> data) {
        this.subscriptionId = subscriptionId;
        this.data = data;
    }

    public BellModel() {

    }

    public final static class Data {
        private String id;
        private String type;
        private Ring_info ring_info;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Ring_info getRing_info() {
            return ring_info;
        }

        public void setRing_info(Ring_info ring_info) {
            this.ring_info = ring_info;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    ", type='" + type + '\'' +
                    ", ring_info=" + ring_info +
                    '}';
        }

        public final static class Ring_info {
            private String type;
            private String value;
            private Metadata metadata;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public Metadata getMetadata() {
                return metadata;
            }

            public void setMetadata(Metadata metadata) {
                this.metadata = metadata;
            }

            @Override
            public String toString() {
                return "Ring_info{" +
                        "type='" + type + '\'' +
                        ", value='" + value + '\'' +
                        ", metadata=" + metadata +
                        '}';
            }

            public final static class Metadata {
                private TimeInstant timeInstant;

                public TimeInstant getTimeInstant() {
                    return timeInstant;
                }

                public void setTimeInstant(TimeInstant timeInstant) {
                    this.timeInstant = timeInstant;
                }

                @Override
                public String toString() {
                    return "Metadata{" +
                            "timeInstant=" + timeInstant +
                            '}';
                }

                @lombok.Data
                public final static class TimeInstant {
                    private String type;
                    public String value;

                }
            }
        }
    }


}
