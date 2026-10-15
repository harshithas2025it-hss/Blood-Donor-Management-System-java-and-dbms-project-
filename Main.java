
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== BLOOD DONOR MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Donor");
            System.out.println("2. View All Donors");
            System.out.println("3. Search Donors");
            System.out.println("4. Add Blood Request");
            System.out.println("5. View Blood Requests");
            System.out.println("6. Update Blood Request Status");
            System.out.println("7. Delete Donor");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addDonor();
                    break;

                case 2:
                    viewDonors();
                    break;

                case 3:
                    searchDonors();
                    break;
                case 4:
                    addBloodRequest();
                    break;
                case 5:
                    viewBloodRequests();
                    break;
                case 6:
                     updateBloodRequestStatus();
                     break;
                case 7:
                    deleteDonor();
                    break;
                case 8:
                   System.out.println("Thank you for using the system !");
                   break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 8);

        sc.close();
    }

    static void addDonor() {

        System.out.println("\n--- Add Donor ---");

        System.out.print("Enter donor name: ");
        String name = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter gender: ");
        String gender = sc.nextLine();

        System.out.print("Enter blood group: ");
        String bloodGroup = sc.nextLine();

        System.out.print("Enter phone no: ");
        String phone = sc.nextLine();

        System.out.print("Enter city: ");
        String city = sc.nextLine();

        String sql = "INSERT INTO Donor "
                + "(donor_name, age, gender, blood_group, phone, city) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, bloodGroup);
            ps.setString(5, phone);
            ps.setString(6, city);

            ps.executeUpdate();

            System.out.println("Donor details saved successfully!");

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error saving donor: " + e.getMessage());
        }
    }

    static void viewDonors() {

        System.out.println("\n--- All Donors ---");

        String sql = "SELECT * FROM Donor";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("\nDonor ID: " + rs.getInt("donor_id"));
                System.out.println("Name: " + rs.getString("donor_name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("Blood Group: " + rs.getString("blood_group"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("City: " + rs.getString("city"));
                System.out.println("--------------------------");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error viewing donors: " + e.getMessage());
        }
    }

    static void searchDonors() {

        System.out.println("\n--- Search Donors ---");

        System.out.print("Enter blood group to search: ");
        String bloodGroup = sc.nextLine();

        System.out.print("Enter city to search: ");
        String city = sc.nextLine();

        String sql = "SELECT * FROM Donor "
                + "WHERE blood_group = ? AND city = ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, bloodGroup);
            ps.setString(2, city);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("\nDonor ID: " + rs.getInt("donor_id"));
                System.out.println("Name: " + rs.getString("donor_name"));
                System.out.println("Blood Group: " + rs.getString("blood_group"));
                System.out.println("Phone: " + rs.getString("phone"));
                System.out.println("City: " + rs.getString("city"));
                System.out.println("--------------------------");
            }

            if (!found) {
                System.out.println("No matching donors found.");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error searching donors: " + e.getMessage());
        }
    }

    static void addBloodRequest() {

        System.out.println("\n--- Add Blood Request ---");

        System.out.print("Enter patient name: ");
        String patientName = sc.nextLine();

        System.out.print("Enter blood group required: ");
        String bloodGroup = sc.nextLine();

        System.out.print("Enter hospital name: ");
        String hospital = sc.nextLine();

        System.out.print("Enter city: ");
        String city = sc.nextLine();

        System.out.print("Enter units required: ");
        int units = sc.nextInt();
        sc.nextLine();

        String sql = "INSERT INTO BloodRequest "
                + "(patient_name, blood_group, hospital, city, units_required) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, patientName);
            ps.setString(2, bloodGroup);
            ps.setString(3, hospital);
            ps.setString(4, city);
            ps.setInt(5, units);

            ps.executeUpdate();

            System.out.println("Blood request saved successfully!");

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error saving blood request: "
                    + e.getMessage());
        }
    }

    static void viewBloodRequests() {

        System.out.println("\n--- All Blood Requests ---");

        String sql = "SELECT * FROM BloodRequest";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println("\nRequest ID: "
                        + rs.getInt("request_id"));
                System.out.println("Patient: "
                        + rs.getString("patient_name"));
                System.out.println("Blood Group: "
                        + rs.getString("blood_group"));
                System.out.println("Hospital: "
                        + rs.getString("hospital"));
                System.out.println("City: "
                        + rs.getString("city"));
                System.out.println("Units Required: "
                        + rs.getInt("units_required"));
                System.out.println("Status: "
                        + rs.getString("request_status"));
                System.out.println("--------------------------");
            }

            if (!found) {
                System.out.println("No blood requests found.");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error viewing blood requests: "
                    + e.getMessage());
        }
    }

    static void updateBloodRequestStatus() {

        System.out.println("\n--- Update Blood Request Status ---");

        System.out.print("Enter request ID: ");
        int requestId = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter new status (Pending/Completed): ");
        String status = sc.nextLine();

        String sql = "UPDATE BloodRequest SET request_status = ? "
                + "WHERE request_id = ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, status);
            ps.setInt(2, requestId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Blood request status updated successfully!");
            } else {
                System.out.println("Request ID not found.");
            }

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error updating status: " + e.getMessage());
        }
    }


    static void deleteDonor() {

        System.out.println("\n--- Delete Donor ---");

        System.out.print("Enter donor ID to delete: ");
        int donorId = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM Donor WHERE donor_id = ?";

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, donorId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Donor deleted successfully!");
            } else {
                System.out.println("Donor ID not found.");
            }

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error deleting donor: " + e.getMessage());
        }
    }

}
