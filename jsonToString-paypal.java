import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

class JsonSerializer {

    public static String toJson(Object value) {
        if (value == null) return "null";
        if (value instanceof String || value instanceof Character) return quote(value.toString());
        if (value instanceof Number || value instanceof Boolean) return value.toString();
        if (value instanceof Map<?, ?> map) return serializeMap(map);
        if (value instanceof Iterable<?> iter) return serializeIterable(iter);
        if (value.getClass().isArray()) return serializeArray(value);
        return quote(value.toString());
    }

    private static String serializeMap(Map<?, ?> map) {
        StringJoiner sj = new StringJoiner(", ", "{", "}");
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            sj.add(quote(String.valueOf(entry.getKey())) + ": " + toJson(entry.getValue()));
        }
        return sj.toString();
    }

    private static String serializeIterable(Iterable<?> iter) {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        for (Object element : iter) sj.add(toJson(element));
        return sj.toString();
    }

    private static String serializeArray(Object array) {
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        int len = Array.getLength(array);
        for (int i = 0; i < len; i++) sj.add(toJson(Array.get(array, i)));
        return sj.toString();
    }

    private static String quote(String s) {
        StringBuilder sb = new StringBuilder(s.length() + 2).append('"');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"':  sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                case '\b': sb.append("\\b");  break;
                case '\f': sb.append("\\f");  break;
                case '\n': sb.append("\\n");  break;
                case '\r': sb.append("\\r");  break;
                case '\t': sb.append("\\t");  break;
                default:
                    if (c < 0x20) sb.append(String.format("\\u%04x", (int) c));
                    else sb.append(c);
            }
        }
        return sb.append('"').toString();
    }

    public static void main(String[] args) {
        Map<String, Object> demo = new LinkedHashMap<>();

        demo.put("string", "Alice");
        demo.put("stringWithEscapes", "quote:\" backslash:\\ newline:\n tab:\t");
        demo.put("emptyString", "");
        demo.put("character", 'A');

        demo.put("intValue", 30);
        demo.put("longValue", 9_000_000_000L);
        demo.put("doubleValue", 3.14);
        demo.put("bigDecimal", new BigDecimal("100.234"));
        demo.put("booleanTrue", true);
        demo.put("booleanFalse", false);
        demo.put("nullValue", null);

        demo.put("emptyList", List.of());
        demo.put("emptyMap", new LinkedHashMap<>());
        demo.put("hobbies", List.of("reading", "coding"));
        demo.put("tagsSet", new LinkedHashSet<>(List.of("java", "json")));
        demo.put("matrix", List.of(List.of(1, 2, 3), List.of(4, 5, 6)));
        demo.put("listWithNull", Arrays.asList("x", null, "y"));

        demo.put("primitiveArray", new int[]{2, 3, 5, 7});
        demo.put("objectArray", new String[]{"a", "b", "c"});

        Map<String, Object> address = new LinkedHashMap<>();
        address.put("city", "Bengaluru");
        address.put("zipcode", "560001");
        demo.put("address", address);

        List<Map<String, Object>> phones = new ArrayList<>();
        Map<String, Object> phone = new LinkedHashMap<>();
        phone.put("type", "home");
        phone.put("number", "+91-9876543210");
        phone.put("verified", true);
        phones.add(phone);
        demo.put("phones", phones);

        System.out.println(toJson(demo));
    }
}
