package HWork8;

import java.awt.*;

public class MyWindow extends Frame {

    GameCore gc;


    public MyWindow () {
        setTitle("OX Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(300, 300, 400, 400);
    }

    public void createWindow () {
        gc = new GameCore();
        buttonArr = new JButton[MAP_SIZE][MAP_SIZE];

        JPanel grid = new JPanel();

        GridLayout layout = new GridLayout(MAP_SIZE, MAP_SIZE);

        grid.setLayout(layout);

        gc.initMAP();
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                buttonArr[i][j] = new JButton();
                grid.add(buttonArr[i][j]);
            }
        }
        container = getContentPane();

        sizeLeftBut.setText("SU");
        sizeValueBut.setText(String.valueOf(BUF_MAP_SIZE));
        sizeRightBut.setText("SD");

        winSizeLeftBut.setText("WU");
        winSizeValueBut.setText(String.valueOf(BUF_WIN_LENGTH));
        winSizeRightBut.setText("WD");

        JPanel upGrid = new JPanel();

        upGrid.setLayout(new GridLayout(3,2));

        upGrid.add(sizeLeftBut);
        upGrid.add(winSizeLeftBut);
        upGrid.add(sizeValueBut);
        upGrid.add(winSizeValueBut);
        upGrid.add(sizeRightBut);
        upGrid.add(winSizeRightBut);

        upGrid.setPreferredSize(new Dimension(80,40));

        container.add(grid, BorderLayout.CENTER);

        gameResult.setText("Play!");
        container.add(gameResult, BorderLayout.SOUTH);

        restartButton.setText("Restart?");
        container.add(restartButton, BorderLayout.NORTH);

        container.add(upGrid, BorderLayout.WEST);

        repaint();

        setVisible(true);
    }

    public void changeDimensionsListen () {
        sizeLeftBut.addActionListener(new ActionListener() {
                                          @Override
                                          public void actionPerformed(ActionEvent actionEvent) {
                                              if (BUF_MAP_SIZE < 9){
                                                  BUF_MAP_SIZE += 1;
                                              }
                                              sizeValueBut.setText(String.valueOf(BUF_MAP_SIZE));
                                              winSizeValueBut.setText(String.valueOf(BUF_WIN_LENGTH));
                                              repaint();
                                          }
                                      }
        );
        sizeRightBut.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent actionEvent) {
                                               if (BUF_MAP_SIZE > 3){
                                                   BUF_MAP_SIZE -= 1;
                                                   if (BUF_WIN_LENGTH > BUF_MAP_SIZE){
                                                       BUF_WIN_LENGTH = BUF_MAP_SIZE;
                                                   }
                                               }
                                               sizeValueBut.setText(String.valueOf(BUF_MAP_SIZE));
                                               winSizeValueBut.setText(String.valueOf(BUF_WIN_LENGTH));
                                               repaint();
                                           }
                                       }
        );
        winSizeLeftBut.addActionListener(new ActionListener() {
                                             @Override
                                             public void actionPerformed(ActionEvent actionEvent) {
                                                 if (BUF_WIN_LENGTH < BUF_MAP_SIZE){
                                                     BUF_WIN_LENGTH += 1;
                                                 }
                                                 sizeValueBut.setText(String.valueOf(BUF_MAP_SIZE));
                                                 winSizeValueBut.setText(String.valueOf(BUF_WIN_LENGTH));
                                                 repaint();
                                             }
                                         }
        );
        winSizeRightBut.addActionListener(new ActionListener() {
                                              @Override
                                              public void actionPerformed(ActionEvent actionEvent) {
                                                  if (BUF_WIN_LENGTH > 2) {
                                                      BUF_WIN_LENGTH -= 1;
                                                  }
                                                  sizeValueBut.setText(String.valueOf(BUF_MAP_SIZE));
                                                  winSizeValueBut.setText(String.valueOf(BUF_WIN_LENGTH));
                                                  repaint();
                                              }
                                          }
        );

    }
    public void restartListen () {
        restartButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent actionEvent) {

                                                container.removeAll();

                                                MAP_SIZE = BUF_MAP_SIZE;
                                                WIN_LENGTH = BUF_WIN_LENGTH;

                                                createWindow();

                                                buttonArrayListen();

                                                gameResult.setText("Play!");

                                                x1 = 0; y1 = 0; x2 = 0; y2 = 0;
                                                draw = true;
                                            }
                                        }
        );
    }
    public void buttonArrayListen () {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                final int k = i;
                final int l = j;
                buttonArr[i][j].setFont(new Font("Arial", Font.PLAIN, buttonArr[i][j].getHeight()-10));
                buttonArr[i][j].addActionListener(new ActionListener() {
                                                      @Override
                                                      public void actionPerformed(ActionEvent actionEvent) {

                                                          if (gc.isCellValid(k,l) && !gc.isWin(DOT_X) && !gc.isWin(DOT_O)){
                                                              buttonArr[k][l].setText("X");
                                                              map[k][l] = 'X';

                                                              if (gc.isWin(DOT_X)){
                                                                  gameResult.setText("Player WIN!");
                                                                  draw = true;
                                                              }

                                                              if (gc.isFull()){
                                                                  gameResult.setText("DRAW!");
                                                              }
                                                              else if (!gc.isWin(DOT_X)){
                                                                  gc.aiTurn();
                                                                  if (gc.isWin(DOT_O)){
                                                                      gameResult.setText("AI WIN!");
                                                                      draw = true;
                                                                  }
                                                              }
                                                              repaint();

                                                          }


                                                      }
                                                  }
                );

            }
        }
    }

    public void paint(Graphics g){
        super.paint(g);
        g.setColor(new Color(200,0,0));
        g.drawLine(x1+80,y1+55,x2+80,y2+55);
    }



}