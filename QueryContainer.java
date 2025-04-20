// Short and crisp
String[] solution(String[][] queries) {
    String[] responses = new String[queries.length];
    List<String> addedQueries = new ArrayList<>();
    for (int i = 0; i < queries.length ; i++) {
        String command = queries[i][0], numValue = queries[i][1];
        switch (command) {
            case "ADD" -> { addedQueries.add(numValue); responses[i] = ""; }
            case "EXISTS" -> responses[i] = addedQueries.contains(numValue) ? "true" : "false";
            case "REMOVE" -> responses[i] = addedQueries.remove(numValue) ? "true" : "false";
            case "GET_NEXT" -> {
                OptionalInt next = addedQueries.stream()
                .mapToInt(Integer::parseInt)
                .filter(n -> n > Integer.parseInt(numValue))
                .min();
                responses[i] = next.isPresent() ? String.valueOf(next.getAsInt()) : "";
            }
        }
    }
    return responses;
}


// First attempt
String[] solution(String[][] queries) {
    String[] responses = new String[queries.length];
    List<String> addedQueries = new ArrayList<>();
    int i = 0;
    for (String[] query : queries) {
        String q = query[0], numValue = query[1];
        switch (q) {
            case "ADD" -> { addedQueries.add(numValue); responses[i++] = ""; }
            case "EXISTS" -> responses[i++] = addedQueries.contains(numValue) ? "true" : "false";
            case "REMOVE" -> responses[i++] = addedQueries.remove(numValue) ? "true" : "false";
            case "GET_NEXT" -> responses[i++] = getNextInt(addedQueries, numValue);
        }
    }
    return responses;
}

String getNextInt(List<String> addedQueries, String numValue) {
    int minInt = Integer.MIN_VALUE;
    int prevMin = Integer.MAX_VALUE;
    for (String curr : addedQueries) {
        if (Integer.valueOf(curr) > Integer.valueOf(numValue)) {
            minInt = Math.min(prevMin, Integer.valueOf(curr));
            prevMin = minInt;
        }
    }
    return minInt == Integer.MIN_VALUE ? "" : String.valueOf(minInt);
}
