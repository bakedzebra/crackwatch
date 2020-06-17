package com.vicefix.crackwatch.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HttpUtils {

    public static QueryParametersBuilder newQueryParametersBuilder() {
        return new QueryParametersBuilder();
    }

    public static class QueryParametersBuilder {
        private final Map<String, Object> queryParameters;

        private QueryParametersBuilder() {
            //hidden

            this.queryParameters = new HashMap<>();
        }

        public QueryParametersBuilder add(String key, Object value) {
            if (Objects.nonNull(key) && Objects.nonNull(value)) {
                this.queryParameters.put(key, value);
            }

            return this;
        }

        public QueryParametersBuilder remove(String key) {
            this.queryParameters.remove(key);
            return this;
        }

        public Map<String, Object> build() {
            return this.queryParameters;
        }

    }
}
