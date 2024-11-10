package validator;

import java.util.List;

public enum QueryType {
    PRODUCT(4, List.of(Type.STRING, Type.INTEGER, Type.INTEGER, Type.STRING_OR_NULL)),
    PROMOTION(5, List.of(Type.STRING, Type.INTEGER, Type.INTEGER, Type.LOCAL_DATE, Type.LOCAL_DATE)),
    CART(2, List.of(Type.STRING, Type.INTEGER));

    private int queryLength;
    private List<Type> queryTypes;

    QueryType(int queryLength, List<Type> queryTypes) {
        this.queryLength = queryLength;
        this.queryTypes = queryTypes;
    }

    public int getQueryLength() {
        return queryLength;
    }

    public List<Type> getQueryTypes() {
        return queryTypes;
    }
}
