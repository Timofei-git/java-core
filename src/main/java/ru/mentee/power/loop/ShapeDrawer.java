package ru.mentee.power.loop;

public class ShapeDrawer {

    final String ERROR = "error";

    public boolean checkValidality(int size){
        if (size <=0) {
            return false;
        }else {
            return true;
        }
    }

    public String drawSquare(int size) {
        if (!checkValidality(size)) return ERROR;
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < size;i++){
            for (int j = 0;j < size;j++){
                str.append("*");
            }
            if (i < size - 1)  str.append("\n");
        }
        return str.toString();
    }

    public String drawEmptySquare(int size) {
        if (!checkValidality(size)) return ERROR;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    str.append("*");
                } else {
                    str.append(" ");
                }
            }
            if (i < size - 1)  str.append("\n");
        }
        return str.toString();
    }

    public String drawTriangle(int height) {
        if (!checkValidality(height)) return ERROR;
        StringBuilder str = new StringBuilder();
        for (int h = 1; h <= height; h++) {
            for (int i = 0; i < h; i++) {
                str.append("*");
            }
            if (h < height) str.append("\n");
        }
        return str.toString();
    }

    public String drawRhombus(int size) {
        if (!checkValidality(size)) return ERROR;
        if (size % 2 == 0 ) {
            size+=1;
        }

        StringBuilder str = new StringBuilder();
        int middle = size / 2;

        // Верхняя половина
        for (int i = 0; i <= middle; i++) {
            for (int j = 0; j < size; j++) {
                if (j >= middle - i && j <= middle + i) {
                    str.append("*");
                } else {
                    str.append(" ");
                }
            }
            str.append("\n"); // перенос всегда, кроме если ромб только из 1 строки
        }

        // Нижняя половина
        for (int i = middle - 1; i >= 0; i--) {
            for (int j = 0; j < size; j++) {
                if (j >= middle - i && j <= middle + i) {
                    str.append("*");
                } else {
                    str.append(" ");
                }
            }
            if (i != 0) { // добавляем перенос только если это не последняя строка ромба
                str.append("\n");
            }
        }

        return str.toString();
    }



    public void printShape(String shape) {
        if (shape.equals(ERROR)) System.out.println("error");
        System.out.println(shape);
    }

    public static void main(String[] args) {
        ShapeDrawer drawer = new ShapeDrawer();

        System.out.println("Square 5x5:");
        drawer.printShape(drawer.drawSquare(5));

        System.out.println("\nEmpty square 5x5:");
        drawer.printShape(drawer.drawEmptySquare(5));

        System.out.println("\nTriangle height 5:");
        drawer.printShape(drawer.drawTriangle(5));

        System.out.println("\nRhombus size 5:");
        drawer.printShape(drawer.drawRhombus(5));
    }
}