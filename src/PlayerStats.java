import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PlayerStats {
    private static JFrame frame;
    private static JPanel panel;
    private static JComboBox<String> playerComboBox;
    private static JButton showStatsButton;
    private static Map<String, Player> playerMap;

    public static void main(String[] args) {
        playerMap = new HashMap<>();
        playerMap.put("Игрок 1", new Player("Игрок 1", 25.3, 7.1, 10.4, 3.2));
        playerMap.put("Игрок 2", new Player("Игрок 2", 22.8, 5.5, 8.3, 2.5));

        frame = new JFrame("Player Statistics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Меню");
        JMenuItem addPlayerMenuItem = new JMenuItem("Добавить игрока");
        menu.add(addPlayerMenuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        addPlayerMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewPlayer();
            }
        });

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        playerComboBox = new JComboBox<>(playerMap.keySet().toArray(new String[0]));
        panel.add(playerComboBox);

        showStatsButton = new JButton("Показать статистику");
        panel.add(showStatsButton);

        showStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPlayer = (String) playerComboBox.getSelectedItem();
                if (selectedPlayer != null) {
                    showPlayerStats(selectedPlayer);
                }
            }
        });

        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }

    private static void showPlayerStats(String playerName) {
        Player player = playerMap.get(playerName);
        if (player != null) {
            panel.add(new JLabel("Имя: " + player.getName()));
            panel.add(new JLabel("Очки: " + player.getPoints()));
            panel.add(new JLabel("Передачи: " + player.getAssists()));
            panel.add(new JLabel("Подборы: " + player.getRebounds()));
            panel.add(new JLabel("Потери: " + player.getTurnovers()));
            frame.revalidate();
        }
    }

    private static void addNewPlayer() {
        JTextField nameField = new JTextField(10);
        JTextField pointsField = new JTextField(5);
        JTextField assistsField = new JTextField(5);
        JTextField reboundsField = new JTextField(5);
        JTextField turnoversField = new JTextField(5);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Имя:"));
        myPanel.add(nameField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Очки:"));
        myPanel.add(pointsField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Передачи:"));
        myPanel.add(assistsField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Подборы:"));
        myPanel.add(reboundsField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Потери:"));
        myPanel.add(turnoversField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Введите данные нового игрока", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            double points = Double.parseDouble(pointsField.getText());
            double assists = Double.parseDouble(assistsField.getText());
            double rebounds = Double.parseDouble(reboundsField.getText());
            double turnovers = Double.parseDouble(turnoversField.getText());

            Player newPlayer = new Player(name, points, assists, rebounds, turnovers);
            playerMap.put(name, newPlayer);
            playerComboBox.addItem(name);
        }
    }
}

class Player {
    private String name;
    private double points;
    private double assists;
    private double rebounds;
    private double turnovers;

    public Player(String name, double points, double assists, double rebounds, double turnovers) {
        this.name = name;
        this.points = points;
        this.assists = assists;
        this.rebounds = rebounds;
        this.turnovers = turnovers;
    }

    public String getName() {
        return name;
    }

    public double getPoints() {
        return points;
    }

    public double getAssists() {
        return assists;
    }

    public double getRebounds() {
        return rebounds;
    }

    public double getTurnovers() {
        return turnovers;
    }
}
