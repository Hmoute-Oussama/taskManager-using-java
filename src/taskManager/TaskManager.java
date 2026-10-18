package taskManager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    private final String FILE_NAME = "tasks.txt";

    public TaskManager() {
        loadTasks();
    }

    // Ajouter une tâche
    public void addTask(String title, String description) {
        Task task = new Task(nextId, title, description);
        tasks.add(task);
        nextId++;

        saveTasks();
        System.out.println("Tache ajoutee avec succes !");
    }

    // Afficher toutes les tâches
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Aucune tache disponible.");
            return;
        }

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    // Rechercher une tâche par ID
    private Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }

    // Terminer une tâche
    public void completeTask(int id) {
        Task task = findTaskById(id);

        if (task == null) {
            System.out.println("Tache introuvable.");
            return;
        }

        task.setCompleted(true);
        saveTasks();

        System.out.println("Tache terminee avec succes !");
    }

    // Supprimer une tâche
    public void deleteTask(int id) {
        Task task = findTaskById(id);

        if (task == null) {
            System.out.println("Tache introuvable.");
            return;
        }

        tasks.remove(task);
        saveTasks();

        System.out.println("Tache supprimee avec succes !");
    }

    // Rechercher par titre
    public void searchTask(String keyword) {
        boolean found = false;

        for (Task task : tasks) {
            if (task.getTitle()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                System.out.println(task);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Aucune tache trouvee.");
        }
    }

    // Afficher les statistiques
    public void displayStatistics() {
        int completed = 0;

        for (Task task : tasks) {
            if (task.isCompleted()) {
                completed++;
            }
        }

        int pending = tasks.size() - completed;

        System.out.println("Total des taches : " + tasks.size());
        System.out.println("Taches terminees : " + completed);
        System.out.println("Taches en cours : " + pending);
    }

    // Sauvegarder les tâches dans un fichier
    private void saveTasks() {
        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Task task : tasks) {
                writer.write(
                    task.getId() + ";"
                    + encode(task.getTitle()) + ";"
                    + encode(task.getDescription()) + ";"
                    + task.isCompleted()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Erreur de sauvegarde : "
                    + e.getMessage());
        }
    }

    // Charger les tâches depuis le fichier
    private void loadTasks() {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";", -1);

                if (data.length != 4) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(data[0]);
                    String title = decode(data[1]);
                    String description = decode(data[2]);
                    boolean completed =
                            Boolean.parseBoolean(data[3]);

                    Task task = new Task(id, title, description);
                    task.setCompleted(completed);

                    tasks.add(task);

                    if (id >= nextId) {
                        nextId = id + 1;
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println(
                            "Une ligne invalide a ete ignoree.");
                }
            }

        } catch (IOException e) {
            // Le fichier n'existe pas encore :
            // ce sera le premier lancement.
        }
    }

    // Eviter les séparateurs et retours à la ligne dans le fichier
    private String encode(String text) {
        return text.replace("%", "%25")
                   .replace(";", "%3B")
                   .replace("\n", "%0A")
                   .replace("\r", "%0D");
    }

    private String decode(String text) {
        return text.replace("%0D", "\r")
                   .replace("%0A", "\n")
                   .replace("%3B", ";")
                   .replace("%25", "%");
    }
}

