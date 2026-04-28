import java.util.*;

class Solution {
    class Poly {
        // Map: List of sorted variables -> Coefficient
        Map<List<String>, Integer> map = new HashMap<>();

        Poly() {}
        Poly(int n) { map.put(new ArrayList<>(), n); }
        Poly(String s) {
            List<String> vars = new ArrayList<>();
            vars.add(s);
            map.put(vars, 1);
        }

        Poly add(Poly other) {
            Poly res = new Poly();
            res.map.putAll(this.map);
            for (List<String> k : other.map.keySet()) {
                res.map.put(k, res.map.getOrDefault(k, 0) + other.map.get(k));
            }
            return res;
        }

        Poly sub(Poly other) {
            Poly res = new Poly();
            res.map.putAll(this.map);
            for (List<String> k : other.map.keySet()) {
                res.map.put(k, res.map.getOrDefault(k, 0) - other.map.get(k));
            }
            return res;
        }

        Poly mul(Poly other) {
            Poly res = new Poly();
            for (List<String> k1 : this.map.keySet()) {
                for (List<String> k2 : other.map.keySet()) {
                    List<String> combined = new ArrayList<>(k1);
                    combined.addAll(k2);
                    Collections.sort(combined);
                    res.map.put(combined, res.map.getOrDefault(combined, 0) + this.map.get(k1) * other.map.get(k2));
                }
            }
            return res;
        }

        List<String> format() {
            List<List<String>> keys = new ArrayList<>(map.keySet());
            // 1. Sort by degree (size) desc, 2. Alphabetical asc
            keys.sort((a, b) -> {
                if (a.size() != b.size()) return b.size() - a.size();
                for (int i = 0; i < a.size(); i++) {
                    int cmp = a.get(i).compareTo(b.get(i));
                    if (cmp != 0) return cmp;
                }
                return 0;
            });

            List<String> res = new ArrayList<>();
            for (List<String> key : keys) {
                int coeff = map.get(key);
                if (coeff == 0) continue;
                StringBuilder sb = new StringBuilder();
                sb.append(coeff);
                for (String s : key) sb.append("*").append(s);
                res.add(sb.toString());
            }
            return res;
        }
    }

    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
        Map<String, Integer> evalMap = new HashMap<>();
        for (int i = 0; i < evalvars.length; i++) evalMap.put(evalvars[i], evalints[i]);

        Stack<Poly> operands = new Stack<>();
        Stack<Character> ops = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                int val = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) 
                    val = val * 10 + (expression.charAt(i++) - '0');
                operands.push(new Poly(val)); i--;
            } else if (Character.isLetter(c)) {
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && Character.isLetter(expression.charAt(i))) 
                    sb.append(expression.charAt(i++));
                String var = sb.toString();
                operands.push(evalMap.containsKey(var) ? new Poly(evalMap.get(var)) : new Poly(var)); i--;
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(') apply(operands, ops);
                ops.pop();
            } else if (c == '+' || c == '-' || c == '*') {
                while (!ops.isEmpty() && ops.peek() != '(' && precedence(ops.peek()) >= precedence(c)) apply(operands, ops);
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) apply(operands, ops);
        return operands.pop().format();
    }

    private void apply(Stack<Poly> operands, Stack<Character> ops) {
        Poly b = operands.pop();
        Poly a = operands.pop();
        char op = ops.pop();
        if (op == '+') operands.push(a.add(b));
        else if (op == '-') operands.push(a.sub(b));
        else operands.push(a.mul(b));
    }

    private int precedence(char op) {
        return (op == '*') ? 2 : 1;
    }
}
