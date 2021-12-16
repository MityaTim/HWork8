package HWork8;

import java.awt.*;

public class GameCore {
    public static void initMAP() {
        map = new char[MAP_SIZE][MAP_SIZE];
        for ( int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++){
                map[i][j];
            }
    }

    public static boolean isCellValid(int y, int x){
        if ((y < 0) || (x < 0) || (y >= MAP_SIZE) || (x >= MAP_SIZE)) {
            return false;
        }
        return (map[y][x] == DOT_EMPTY);
    }

    public static boolean aiCheckHorizon(int stepToWin){
        int horizonCount;
        for (int i = 0; i < MAP_SIZE; i++) {
            horizonCount = 0;
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == DOT_X){
                    horizonCount++;
                }
                else{
                    horizonCount = 0;
                }
                if (horizonCount == WIN_LENGTH-stepToWin){
                    if (stepToWin > 1) {
                        if ((isCellValid(i, j + 1) == true) && (isCellValid(i, j - horizonCount) == true)){
                            map[i][j + 1] = DOT_O;
                            buttonArr[i][j + 1].setText("0");
                            return true;
                        }
                    }
                    else {
                        if (isCellValid(i, j + 1) == true) { // пробуем поставить правее
                            map[i][j + 1] = DOT_O;
                            buttonArr[i][j + 1].setText("0");
                            return true;
                        } else if (isCellValid(i, j - horizonCount) == true) {
                            map[i][j - horizonCount] = DOT_O;
                            buttonArr[i][j - horizonCount].setText("0");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean aiCheckVertical(int stepToWin) {
        int verticalCount;
        for (int i = 0; i < MAP_SIZE; i++) {
            verticalCount = 0;
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[j][i] == DOT_X){
                    verticalCount++;
                }
                else{
                    verticalCount = 0;
                }
                if (verticalCount == WIN_LENGTH-stepToWin){

                    if (stepToWin > 1){
                        if ((isCellValid(j+1,i) == true) && (isCellValid(j-verticalCount,i) == true)){
                            map[j+1][i] = DOT_O;
                            buttonArr[j+1][i].setText("0");
                            return true;
                        }
                    }
                    else {
                        if (isCellValid(j+1,i) == true) {
                            map[j+1][i] = DOT_O;
                            buttonArr[j+1][i].setText("0");
                            return true;
                        }
                        else if (isCellValid(j-verticalCount,i) == true) {
                            map[j-verticalCount][i] = DOT_O;
                            buttonArr[j-verticalCount][i].setText("0");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean aiCheckLeftDiagonal(int stepToWin) {
        int lrdiagonalCount = 0;
        for (int i = 0; i < MAP_SIZE; i++) {
            if (map[i][i] == DOT_X){
                lrdiagonalCount++;
            }
            else{
                lrdiagonalCount = 0;
            }

            if (lrdiagonalCount == WIN_LENGTH-stepToWin){

                if (stepToWin > 1) {
                    if ((isCellValid(i + 1, i + 1) == true) && (isCellValid((i - lrdiagonalCount), (i - lrdiagonalCount)) == true)){
                        map[i + 1][i + 1] = DOT_O;
                        buttonArr[i + 1][i + 1].setText("0");
                        return true;
                    }
                }
                else {
                    if (isCellValid(i + 1, i + 1) == true) {
                        map[i + 1][i + 1] = DOT_O;
                        buttonArr[i + 1][i + 1].setText("0");
                        return true;
                    } else if (isCellValid((i - lrdiagonalCount), (i - lrdiagonalCount)) == true) {
                        map[(i - lrdiagonalCount)][i - lrdiagonalCount] = DOT_O;
                        buttonArr[(i - lrdiagonalCount)][i - lrdiagonalCount].setText("0");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean aiCheckRightDiagonal(int stepToWin) {
        int rldiagonalCount = 0; // горизонтальный счетчик справа налево последовательных совпадений пользователя
        for (int i = 0; i < MAP_SIZE; i++) {
            if (map[MAP_SIZE-1-i][i] == DOT_X){
                rldiagonalCount++;
            }
            else{
                rldiagonalCount = 0;
            }

            if (rldiagonalCount == WIN_LENGTH-stepToWin){
                if (stepToWin > 1) {
                    if ((isCellValid(MAP_SIZE - 1 - (i + 1), i + 1) == true) && (isCellValid(MAP_SIZE - 1 - (i - rldiagonalCount), (i - rldiagonalCount)) == true)){
                        map[MAP_SIZE - 1 - (i + 1)][i + 1] = DOT_O;
                        buttonArr[MAP_SIZE - 1 - (i + 1)][i + 1].setText("0");
                        return true;
                    }
                }
                else {
                    if (isCellValid(MAP_SIZE - 1 - (i + 1), i + 1) == true) { // пробуем поставить впереди по диагонали
                        map[MAP_SIZE - 1 - (i + 1)][i + 1] = DOT_O;
                        buttonArr[MAP_SIZE - 1 - (i + 1)][i + 1].setText("0");
                        return true;
                    } else if (isCellValid(MAP_SIZE - 1 - (i - rldiagonalCount), (i - rldiagonalCount)) == true) {
                        map[MAP_SIZE - 1 - (i - rldiagonalCount)][i - rldiagonalCount] = DOT_O;
                        buttonArr[MAP_SIZE - 1 - (i - rldiagonalCount)][i - rldiagonalCount].setText("0");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void aiTurn(){
        boolean ai_put_data = false; // заводим флаг, если где то в проверках поставим точку от ИИ, то флаг становится true и в следующие проверки не входим
        int k = 1; // дополнительный счетчик для удобства работы с диагоналями
        // такой флаг удобен тем, что можно не добавлять ни одну проверку и алгоритм дойдет до случайной установки
        // значений, те до алгоритма изначального: ставим значений в случайное свободное место

        //1.1
        // защита от того что у пользователя не хватает одного хода до победы
        // те проверяем если где то есть линия равна по длине рядом стоящий Х более WIN_LENGTH - 1,
        // при этом остальные свободные
        // если находим такую линию, то ставим слева от нее или справа, где удается 0
        // проверяем как пользователь расставил горизонтали

        // проверка горизонталей, что пользователь уже в одном шаге от победы в первой найденной горизонтали
        if (ai_put_data == false){ // здесь эта проверка не нужна, но лучше оставить, тк вдруг сверху что новое добавиться, чтобы потом не забыть
            ai_put_data = aiCheckHorizon(1);
        }
        // проверка вертикалей, что пользователь уже в одном шаге от победы по первой вертикале
        if (ai_put_data == false){
            ai_put_data = aiCheckVertical(1);
        }

        // проверка центральной диагонали с лева на право, что пользователь в одном шаге до победы
        if (ai_put_data == false){
            ai_put_data = aiCheckLeftDiagonal(1);
        }

        // проверка центральной диагонали справа на лево, что пользователь в одном шаге до победы
        if (ai_put_data == false) {
            ai_put_data = aiCheckRightDiagonal(1);
        }

        //1.2
        // защита от того что у пользователя не хватает двух ходов до победы
        // те проверяем если где то есть линия равна по длине рядом стоящий Х более WIN_LENGTH - 2,
        // при этом остальные свободные, те слева и справа от двойки одновременно
        // если находим такую линию, то ставим слева от нее или справа, где удается 0

        // проверка горизонталей
        if (ai_put_data == false){ // здесь эта проверка не нужна, но лучше оставить, тк вдруг сверху что новое добавиться, чтобы потом не забыть
            ai_put_data = aiCheckHorizon(2);
        }
        // проверка вертикалей
        if (ai_put_data == false){
            ai_put_data = aiCheckVertical(2);
        }

        // проверка центральной диагонали с лева на право
        if (ai_put_data == false){
            ai_put_data = aiCheckLeftDiagonal(2);
        }

        // проверка центральной диагонали справа на лево
        if (ai_put_data == false) {
            ai_put_data = aiCheckRightDiagonal(2);
        }


        //2.
        // если защита была не нужа и мы дошли в итоге сюда то следует найти свои символы и к ним добавить 0
        // в нужную сторону, где есть еще место

        //3.
        // если в итоге мы дашли сюда, то ставим в любое свободное ставим
        if (ai_put_data == false){
            int x, y;
            do {
                x = random.nextInt(MAP_SIZE);
                y = random.nextInt(MAP_SIZE);
            } while (!isCellValid(y, x));
            map[y][x] = DOT_O;
            buttonArr[y][x].setText("0");
        }
    };

    public static boolean isFull() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isWin(char c){
        int isWinCount;
        Point loc;
        // проверяем все горизонтали на всю длину MAP_SIZE
        for (int i = 0; i < MAP_SIZE; i++) {
            isWinCount = 0; // обнуляем счетчик проверки длины
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == c){
                    isWinCount++; // если соответствует, то инкрементируем счетчик
                }
                else {
                    isWinCount = 0; // если нет то обнуляем
                }
                loc = buttonArr[i][j].getLocation();
                loc.y += buttonArr[i][j].getHeight() / 2;
                if (isWinCount == 1){
                    bx1 = loc.x;
                    by1 = loc.y;
                }

                if (isWinCount == WIN_LENGTH){ // если подряд было не менее параметра WIN_LENGHT, то победа
                    bx2 = loc.x + buttonArr[i][j].getWidth();
                    by2 = loc.y;
                    x1 = bx1;
                    x2 = bx2;
                    y1 = by1;
                    y2 = by2;
                    return true;
                }
            }
        }
        // проверяем все вертикали на всю длину MAP_SIZE
        for (int i = 0; i < MAP_SIZE; i++) {
            isWinCount = 0; // обнуляем счетчик проверки длины
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[j][i] == c){
                    isWinCount++; // если соответствует, то инкрементируем счетчик
                }
                else {
                    isWinCount = 0; // если нет то обнуляем
                }
                loc = buttonArr[j][i].getLocation();
                loc.x += buttonArr[j][i].getWidth() / 2;

                if (isWinCount == 1){
                    bx1 = loc.x;
                    by1 = loc.y;
                }

                if (isWinCount == WIN_LENGTH){ // если подряд было не менее параметра WIN_LENGHT, то победа
                    bx2 = loc.x;
                    by2 = loc.y + buttonArr[j][i].getHeight();
                    x1 = bx1;
                    x2 = bx2;
                    y1 = by1;
                    y2 = by2;
                    return true;
                }
            }
        }
        // проверяем диагональ слева на право
        isWinCount = 0; // обнуляем счетчик проверки длины
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if ((map[i][j] == c) && (i == j)){
                    isWinCount++; // если соответствует, то инкрементируем счетчик
                }
                else if (i == j) {
                    isWinCount = 0; // если нет то обнуляем (только по диагонали)
                }
                loc = buttonArr[i][j].getLocation();
                if ((isWinCount == 1) && (i == j)){
                    bx1 = loc.x;
                    by1 = loc.y;
                }
                if (isWinCount == WIN_LENGTH){ // если подряд было не менее параметра WIN_LENGHT, то победа
                    bx2 = loc.x + buttonArr[i][j].getWidth();
                    by2 = loc.y + buttonArr[i][j].getHeight();
                    x1 = bx1;
                    x2 = bx2;
                    y1 = by1;
                    y2 = by2;
                    return true;
                }
            }
        }
        // проверяем диагональ справа на лево
        isWinCount = 0; // обнуляем счетчик проверки длины
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if ((map[i][j] == c) && (i == MAP_SIZE - 1 - j)){
                    isWinCount++; // если соответствует, то инкрементируем счетчик
                }
                else if (i == MAP_SIZE - 1 - j){
                    isWinCount = 0; // если нет то обнуляем (только по диагонали)
                }
                loc = buttonArr[i][j].getLocation();
                if ((isWinCount == 1) && (i == MAP_SIZE - 1 - j)){
                    bx1 = loc.x + buttonArr[i][j].getWidth();
                    by1 = loc.y;
                }
                if (isWinCount == WIN_LENGTH){ // если подряд было не менее параметра WIN_LENGHT, то победа
                    bx2 = loc.x;
                    by2 = loc.y + buttonArr[i][j].getHeight();
                    x1 = bx1;
                    x2 = bx2;
                    y1 = by1;
                    y2 = by2;
                    return true;
                }
            }
        }
        return false; // нет победы
    }
}

}
