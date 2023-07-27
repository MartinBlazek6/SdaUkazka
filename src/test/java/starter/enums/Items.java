package starter.enums;

public enum Items {
       BACKPACK("sauce-labs-backpack"),
       BIKE_LIGHT("sauce-labs-bike-light"),
       BOLT_T_SHIRT("sauce-labs-bolt-t-shirt"),
       FLEECE_JACKET("sauce-labs-fleece-jacket");

        private final String value;

        private Items(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

