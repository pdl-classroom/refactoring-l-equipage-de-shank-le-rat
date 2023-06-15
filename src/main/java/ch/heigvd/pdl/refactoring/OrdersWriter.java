package ch.heigvd.pdl.refactoring;

public class OrdersWriter {

    private Orders orders;

    public OrdersWriter(Orders orders) {
        this.orders = orders;
    }

    public String getContents() {
        StringBuilder sb = new StringBuilder("{\"orders\": [");
    
        for (int i = 0; i < orders.getOrdersCount(); i++) {
            Order order = orders.getOrder(i);
            sb.append("{")
                .append("\"id\": ").append(order.getOrderId()).append(", ")
                .append("\"products\": [");
            for (int j = 0; j < order.getProductsCount(); j++) {
                Product product = order.getProduct(j);
                sb.append("{")
                    .append("\"code\": \"").append(product.getCode()).append("\", ")
                    .append("\"color\": \"").append(getColorFor(product)).append("\", ");
        
                if (product.getSize() != Product.SIZE_NOT_APPLICABLE) {
                    sb.append("\"size\": \"").append(getSizeFor(product)).append("\", ");
                }
        
                sb.append("\"price\": ").append(product.getPrice()).append(", ")
                    .append("\"currency\": \"").append(product.getCurrency()).append("\"}");
        
                if (j < order.getProductsCount() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]")
                .append("}, ");
        }
    
        if (orders.getOrdersCount() > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
    
        sb.append("]}");
        return sb.toString();
    }

    private String getSizeFor(Product product) {
        switch (product.getSize()) {
            case 1:
                return "XS";
            case 2:
                return "S";
            case 3:
                return "M";
            case 4:
                return "L";
            case 5:
                return "XL";
            case 6:
                return "XXL";
            default:
                return "Invalid Size";
        }
    }

    private String getColorFor(Product product) {
        switch (product.getColor()) {
            case 1:
                return "blue";
            case 2:
                return "red";
            case 3:
                return "yellow";
            default:
                return "no color";
        }
    }
}