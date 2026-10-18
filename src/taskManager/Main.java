package taskManager;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager manager = new TaskManager();

        boolean running = true;

        while (running) {
            System.out.println("\n===== TASK MANAGER =====");
            System.out.println("1. Ajouter une tache");
            System.out.println("2. Afficher les taches");
            System.out.println("3. Terminer une tache");
            System.out.println("4. Supprimer une tache");
            System.out.println("5. Rechercher une tache");
            System.out.println("6. Afficher les statistiques");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {

                    case "1":
                        System.out.print("Titre : ");
                        String title = scanner.nextLine().trim();

                        System.out.print("Description : ");
                        String description = scanner.nextLine().trim();

                        if (title.isEmpty()) {
                            System.out.println(
                                    "Le titre ne peut pas etre vide.");
                        } else {
                            manager.addTask(title, description);
                        }
                        break;

                    case "2":
                        manager.displayTasks();
                        break;

                    case "3":
                        System.out.print("ID de la tache : ");
                        int completeId = Integer.parseInt(
                                scanner.nextLine().trim());

                        manager.completeTask(completeId);
                        break;

                    case "4":
                        System.out.print("ID a supprimer : ");
                        int deleteId = Integer.parseInt(
                                scanner.nextLine().trim());

                        manager.deleteTask(deleteId);
                        break;

                    case "5":
                        System.out.print("Titre a rechercher : ");
                        String keyword = scanner.nextLine().trim();

                        manager.searchTask(keyword);
                        break;

                    case "6":
                        manager.displayStatistics();
                        break;

                    case "0":
                        running = false;
                        System.out.println("Au revoir !");
                        break;

                    default:
                        System.out.println(
                                "Choix invalide. Reessayez.");
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Erreur : veuillez saisir un nombre valide.");
            }
        }

        scanner.close();
    }
}

