import java.io.*;
import java.net.*;
import java.util.*;

// Пример URL: http://localhost:8080/calculate?a=5&b=3&op=+

public class CalculateHttpServer {
    private static final int PORT = 8080;
    private static final List<String> notes = new ArrayList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Note HTTP Server запущен на порту " + PORT);
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    handleClient(clientSocket);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка запуска сервера: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
        String line = in.readLine();
        if (line == null) return;
        String[] requestParts = line.split(" ");
        String method = requestParts[0];
        String path = requestParts[1];

        // Обработка арифметического запроса
        if (method.equals("GET") && path.startsWith("/calculate")) {
            handleCalculate(path, out);
        } else if (method.equals("GET") && path.equals("/notes")) {
            handleGetNotes(out);
        } else if (method.equals("POST") && path.startsWith("/addnote")) {
            String note = extractBody(in);
            handleAddNote(note, out);
        } else if (method.equals("POST") && path.startsWith("/delete-note")) {
            handleDeleteLastNote(out);
        } else {
            handleNotFound(out);
        }
        out.flush();
    }

    // Обработка арифметического запроса
    private static void handleCalculate(String path, PrintWriter out) {
        try {
            String[] params = path.split("\\?")[1].split("&");
            int a = Integer.parseInt(params[0].split("=")[1]);
            int b = Integer.parseInt(params[1].split("=")[1]);
            String op = params[2].split("=")[1];

            int result;
            switch (op) {
                case "+":
                    result = a + b;
                    break;
                case "-":
                    result = a - b;
                    break;
                case "*":
                    result = a * b;
                    break;
                case "/":
                    if (b == 0) {
                        sendHttpResponse(out, 400, "Ошибка: Деление на ноль");
                        return;
                    }
                    result = a / b;
                    break;
                default:
                    sendHttpResponse(out, 400, "Ошибка: Неверная операция");
                    return;
            }
            sendHttpResponse(out, 200, String.valueOf(result));
        } catch (Exception e) {
            sendHttpResponse(out, 400, "Ошибка: Неверный запрос");
        }
    }

    private static void handleGetNotes(PrintWriter out) {
        StringBuilder response = new StringBuilder();
        response.append("<html><body><h1>Notes</h1><ul>");
        for (String note : notes) {
            response.append("<li>").append(note).append("</li>");
        }
        response.append("</ul>");
        response.append("<form method='POST' action='/addnote'>")
                .append("<input type='text' name='note' placeholder='Add note' required>")
                .append("<button type='submit'>Add</button>")
                .append("</form>");
        response.append("<form method='POST' action='/delete-note'>")
                .append("<button type='submit'>Delete Last Note</button>")
                .append("</form>");
        response.append("</body></html>");
        sendHttpResponse(out, 200, response.toString());
    }

    private static void handleAddNote(String note, PrintWriter out) {
        if (note != null && !note.isEmpty()) {
            notes.add(note);
            sendHttpRedirect(out, "/notes");
        } else {
            sendHttpResponse(out, 400, "<html><body><h1>Invalid note</h1><a href='/notes'>Back to Notes</a></body></html>");
        }
    }

    private static void handleDeleteLastNote(PrintWriter out) {
        if (!notes.isEmpty()) {
            notes.remove(notes.size() - 1);
            sendHttpRedirect(out, "/notes");
        } else {
            sendHttpResponse(out, 400, "<html><body><h1>No notes to delete</h1><a href='/notes'>Back to Notes</a></body></html>");
        }
    }

    private static void handleNotFound(PrintWriter out) {
        sendHttpResponse(out, 404, "<html><body><h1>404 Not Found</h1></body></html>");
    }

    private static String extractBody(BufferedReader in) throws IOException {
        StringBuilder body = new StringBuilder();
        String line;
        while (!(line = in.readLine()).isEmpty()) {
            // Считываем заголовки
        }
        while (in.ready() && (line = in.readLine()) != null) {
            body.append(line);
        }
        String bodyString = body.toString();
        String[] parts = bodyString.split("=");
        return parts.length > 1 ? parts[1].replace("+", " ") : null;
    }

    private static void sendHttpResponse(PrintWriter out, int statusCode, String body) {
        out.println("HTTP/1.1 " + statusCode + " OK");
        out.println("Content-Type: text/html");
        out.println("Content-Length: " + body.length());
        out.println();
        out.println(body);
    }

    private static void sendHttpRedirect(PrintWriter out, String location) {
        out.println("HTTP/1.1 302 Found");
        out.println("Location: " + location);
        out.println("Content-Length: 0");
        out.println();
    }
}
