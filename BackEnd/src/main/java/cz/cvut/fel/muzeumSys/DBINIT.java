//package cz.cvut.fel.muzeumSys;
//
//import cz.cvut.fel.muzeumSys.model.*;
//import cz.cvut.fel.muzeumSys.model.enums.Color;
//import cz.cvut.fel.muzeumSys.model.enums.Era;
//import cz.cvut.fel.muzeumSys.model.enums.Role;
//import cz.cvut.fel.muzeumSys.model.enums.Type;
//import cz.cvut.fel.muzeumSys.repository.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class DBINIT implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final RoomRepository roomRepository;
//    private final GroupRepository groupRepository;
//    private final ArtRepository artRepository;
//    private final QRCodeRepository qrCodeRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) {
//        if (userRepository.count() > 0) return; // Pokud už data existují, neprovádět znovu
//
//        // Users
//        User admin = new Admin();
//        admin.setEmail("vojta.kratina@gmail.com");
//        admin.setPassword(passwordEncoder.encode("lol"));
//        admin.setRole(Role.ROLE_ADMIN);
//        admin.setFirstName("Admin");
//        admin.setLastName("User");
//        admin.setPhoneNumber("123456789");
//
//        User employee = new Employee();
//        employee.setEmail("employee@muzeum.cz");
//        employee.setPassword(passwordEncoder.encode("lol"));
//        employee.setRole(Role.ROLE_EMPLOYEE);
//        employee.setFirstName("Employee");
//        employee.setLastName("User");
//        employee.setPhoneNumber("987654321");
//
//        User employee1 = new Employee();
//        employee1.setEmail("s.chlustinova@muzum.cz");
//        employee1.setPassword(passwordEncoder.encode("sara"));
//        employee1.setRole(Role.ROLE_EMPLOYEE);
//        employee1.setFirstName("Sara");
//        employee1.setLastName("Chlustinka");
//        employee1.setPhoneNumber("567372937");
//
//        userRepository.saveAll(List.of(admin, employee, employee1));
//
//        // Rooms
//        Room room1 = new Room();
//        room1.setName("Ancient Room");
//        room1.setFloorNumber(1);
//
//        Room room2 = new Room();
//        room2.setName("Modern Room");
//        room2.setFloorNumber(2);
//
//        roomRepository.saveAll(List.of(room1, room2));
//
//        // Groups
//        Group group1 = new Group();
//        group1.setName("Sculptures");
//        group1.setDescription("Stone and metal sculptures");
//
//        Group group2 = new Group();
//        group2.setName("Paintings");
//        group2.setDescription("Oil and watercolor paintings");
//
//        groupRepository.saveAll(List.of(group1, group2));
//
//        // Arts
//        Art art1 = new Art();
//        art1.setName("Venus de Milo");
//        art1.setEra(Era.ANCIENT_GREECE); // ✅ validní enum
//        art1.setType(Type.SCULPTURE);   // ✅ validní enum
//        art1.setAuthor("Unknown");
//        art1.setColor("RED");         // ✅ jako String
//        art1.setDescription("A famous Greek statue.");
//        art1.setParameters("203 cm, Marble");
//        art1.setGroup(group1);
//        art1.setRoom(room1);
//
//        Art art2 = new Art();
//        art2.setName("Starry Night");
//        art2.setEra(Era.POST_IMPRESSIONISM); // ✅ validní enum
//        art2.setType(Type.PAINTING);         // ✅ validní enum
//        art2.setAuthor("Vincent van Gogh");
//        art2.setColor("YELLOW");
//        art2.setDescription("Famous post-impressionist painting.");
//        art2.setParameters("74 cm × 92 cm");
//        art2.setGroup(group2);
//        art2.setRoom(room2);
//
//        Art art3 = new Art();
//        art3.setName("The Thinker");
//        art3.setEra(Era.POST_IMPRESSIONISM);
//        art3.setType(Type.SCULPTURE);
//        art3.setAuthor("Auguste Rodin");
//        art3.setColor("GREEN");
//        art3.setDescription("A sculpture of a man in deep thought.");
//        art3.setParameters("Height: 186 cm");
//        art3.setGroup(group1);
//        art3.setRoom(room1);
//
//        Art art4 = new Art();
//        art4.setName("The Starry Night");
//        art4.setEra(Era.CUBISM);
//        art4.setType(Type.PAINTING);
//        art4.setAuthor("Vincent van Gogh");
//        art4.setColor("YELLOW");
//        art4.setDescription("A famous depiction of a night sky.");
//        art4.setParameters("73.7 cm × 92.1 cm, Oil on canvas");
//        art4.setGroup(group1);
//        art4.setRoom(room1);
//
//        Art art5 = new Art();
//        art5.setName("Terracotta Army Soldier");
//        art5.setEra(Era.MEDIEVAL_GOTHIC);
//        art5.setType(Type.SCULPTURE);
//        art5.setAuthor("Unknown");
//        art5.setColor("RED");
//        art5.setDescription("One of the many clay soldiers from the Terracotta Army.");
//        art5.setParameters("Life-size, Clay");
//        art5.setGroup(group1);
//        art5.setRoom(room1);
//
//
//        artRepository.saveAll(List.of(art1, art2,art3,art4,art5));
//    }
//}
//
//package cz.cvut.fel.muzeumSys;
//
//import cz.cvut.fel.muzeumSys.model.*;
//import cz.cvut.fel.muzeumSys.model.enums.*;
//import cz.cvut.fel.muzeumSys.repository.*;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@RequiredArgsConstructor
//public class DBINIT implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final RoomRepository roomRepository;
//    private final GroupRepository groupRepository;
//    private final ArtRepository artRepository;
//    private final EmergencyRecordRepository emergencyRecordRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) {
//        if (userRepository.count() > 0) return;
//
//        // ===== USERS =====
//        List<User> users = new ArrayList<>();
//        users.add(createUser("vojta.kratina@gmail.com", "lol", "Admin", "Správce", Role.ROLE_ADMIN,"777888999"));
//        users.add(createUser("employee@muzeum.cz", "lol", "Alice", "Kratka", Role.ROLE_EMPLOYEE,"777888998"));
//        users.add(createUser("employee2@muzeum.cz", "pass2", "Bob", "Novák", Role.ROLE_EMPLOYEE,"777888997"));
//        users.add(createUser("employee3@muzeum.cz", "pass3", "Clara", "Mašková", Role.ROLE_EMPLOYEE,"777888996"));
//        users.add(createUser("employee4@muzeum.cz", "pass4", "David", "Liška", Role.ROLE_EMPLOYEE,"777888959"));
//        users.add(createUser("employee5@muzeum.cz", "pass5", "Eva", "Zelená", Role.ROLE_EMPLOYEE,"7778889943"));
//        userRepository.saveAll(users);
//
//        // ===== ROOMS =====
//        List<Room> rooms = new ArrayList<>();
//
//        rooms.add(createRoom("Starověk", 1));
//        rooms.add(createRoom("Renesance", 2));
//        rooms.add(createRoom("Moderní umění", 3));
//        rooms.add(createRoom("Sochařský sál", 2));
//        rooms.add(createRoom("Digitální galerie", 3));
//        rooms.add(createRoom("Gotický výklenek", 1));
//
//        roomRepository.saveAll(rooms);
//
//        // ===== GROUPS =====
//        List<Group> groups = new ArrayList<>();
//        groups.add(createGroup("Obrazy", "Výtvarné malby různých období"));
//        groups.add(createGroup("Sochy", "Kamenné, bronzové a hliněné sochy"));
//        groups.add(createGroup("Digitální umění", "Moderní tvorba s technologiemi"));
//        groups.add(createGroup("Historické objekty", "Artefakty z minulosti"));
//        groups.add(createGroup("Interaktivní instalace", "Multimediální zážitky"));
//
//        groupRepository.saveAll(groups);
//
//        // ===== ARTS =====
//        List<Art> arts = new ArrayList<>();
//
//        arts.add(createArt("Mona Lisa", "Leonardo da Vinci", Era.RENAISSANCE, Type.PAINTING, "Portrét ženy", "77x53cm", "YELLOW", groups.get(0), rooms.get(1)));
//        arts.add(createArt("David", "Michelangelo", Era.RENAISSANCE, Type.SCULPTURE, "Mramorová socha muže", "5.17m", "RED", groups.get(1), rooms.get(3)));
//        arts.add(createArt("Hvězdná noc", "Vincent van Gogh", Era.POST_IMPRESSIONISM, Type.PAINTING, "Slavná noční krajina", "74x92cm", "RED", groups.get(0), rooms.get(2)));
//        arts.add(createArt("Digitální vír", "Eva K.", Era.DIGITAL_ART, Type.DIGITAL_ART, "Interaktivní projekce", "projekce", "YELLOW", groups.get(2), rooms.get(4)));
//        arts.add(createArt("Faraonova maska", "Unknown", Era.ANCIENT_EGYPT, Type.HISTORICAL_OBJECT, "Zlatá pohřební maska", "50x30cm", "RED", groups.get(3), rooms.get(0)));
//        arts.add(createArt("Virtuální les", "Future.Art", Era.DIGITAL_ART, Type.INSTALLATION, "AR prostředí", "VR/AR", "YELLOW", groups.get(4), rooms.get(4)));
//        arts.add(createArt("Guernica", "Pablo Picasso", Era.CUBISM, Type.PAINTING, "Antiválečný obraz", "3.5x7.8m", "RED", groups.get(0), rooms.get(2)));
//        arts.add(createArt("Časová spirála", "DigitArt.cz", Era.DIGITAL_ART, Type.DIGITAL_ART, "Animace v loopu", "animace", "GREEN", groups.get(2), rooms.get(4)));
//        arts.add(createArt("The Thinker", "Auguste Rodin", Era.REALISM, Type.SCULPTURE, "Socha muže v přemýšlení", "186cm", "GREEN", groups.get(1), rooms.get(3)));
//        arts.add(createArt("Středověký štít", "Unknown", Era.MEDIEVAL_GOTHIC, Type.HISTORICAL_OBJECT, "Ozdobený bojový štít", "60cm", "YELLOW", groups.get(3), rooms.get(5)));
//        arts.add(createArt("Mona Lisa", "Leonardo da Vinci", Era.RENAISSANCE, Type.PAINTING, "Portrét ženy", "77x53cm", "YELLOW", groups.get(0), rooms.get(1)));
//        arts.add(createArt("David", "Michelangelo", Era.RENAISSANCE, Type.SCULPTURE, "Mramorová socha muže", "5.17m", "RED", groups.get(1), rooms.get(3)));
//        arts.add(createArt("Hvězdná noc", "Vincent van Gogh", Era.POST_IMPRESSIONISM, Type.PAINTING, "Slavná noční krajina", "74x92cm", "RED", groups.get(0), rooms.get(2)));
//        arts.add(createArt("Digitální vír", "Eva K.", Era.DIGITAL_ART, Type.DIGITAL_ART, "Interaktivní projekce", "projekce", "YELLOW", groups.get(2), rooms.get(4)));
//        arts.add(createArt("Faraonova maska", "Unknown", Era.ANCIENT_EGYPT, Type.HISTORICAL_OBJECT, "Zlatá pohřební maska", "50x30cm", "RED", groups.get(3), rooms.get(0)));
//        arts.add(createArt("Virtuální les", "Future.Art", Era.DIGITAL_ART, Type.INSTALLATION, "AR prostředí", "VR/AR", "YELLOW", groups.get(4), rooms.get(4)));
//        arts.add(createArt("Guernica", "Pablo Picasso", Era.CUBISM, Type.PAINTING, "Antiválečný obraz", "3.5x7.8m", "RED", groups.get(0), rooms.get(2)));
//        arts.add(createArt("Časová spirála", "DigitArt.cz", Era.DIGITAL_ART, Type.DIGITAL_ART, "Animace v loopu", "animace", "GREEN", groups.get(2), rooms.get(4)));
//        arts.add(createArt("The Thinker", "Auguste Rodin", Era.REALISM, Type.SCULPTURE, "Socha muže v přemýšlení", "186cm", "GREEN", groups.get(1), rooms.get(3)));
//        arts.add(createArt("Středověký štít", "Unknown", Era.MEDIEVAL_GOTHIC, Type.HISTORICAL_OBJECT, "Ozdobený bojový štít", "60cm", "YELLOW", groups.get(3), rooms.get(5)));
//        arts.add(createArt("Mona Lisa", "Leonardo da Vinci", Era.RENAISSANCE, Type.PAINTING, "Portrét ženy", "77x53cm", "YELLOW", groups.get(0), rooms.get(1)));
//        arts.add(createArt("David", "Michelangelo", Era.RENAISSANCE, Type.SCULPTURE, "Mramorová socha muže", "5.17m", "RED", groups.get(1), rooms.get(3)));
//        arts.add(createArt("Hvězdná noc", "Vincent van Gogh", Era.POST_IMPRESSIONISM, Type.PAINTING, "Slavná noční krajina", "74x92cm", "RED", groups.get(0), rooms.get(2)));
//        arts.add(createArt("Digitální vír", "Eva K.", Era.DIGITAL_ART, Type.DIGITAL_ART, "Interaktivní projekce", "projekce", "YELLOW", groups.get(2), rooms.get(4)));
//        arts.add(createArt("Faraonova maska", "Unknown", Era.ANCIENT_EGYPT, Type.HISTORICAL_OBJECT, "Zlatá pohřební maska", "50x30cm", "RED", groups.get(3), rooms.get(0)));
//        arts.add(createArt("Virtuální les", "Future.Art", Era.DIGITAL_ART, Type.INSTALLATION, "AR prostředí", "VR/AR", "YELLOW", groups.get(4), rooms.get(4)));
//        arts.add(createArt("Guernica", "Pablo Picasso", Era.CUBISM, Type.PAINTING, "Antiválečný obraz", "3.5x7.8m", "RED", groups.get(0), rooms.get(2)));
//        arts.add(createArt("Časová spirála", "DigitArt.cz", Era.DIGITAL_ART, Type.DIGITAL_ART, "Animace v loopu", "animace", "GREEN", groups.get(2), rooms.get(4)));
//        arts.add(createArt("The Thinker", "Auguste Rodin", Era.REALISM, Type.SCULPTURE, "Socha muže v přemýšlení", "186cm", "GREEN", groups.get(1), rooms.get(3)));
//        arts.add(createArt("Středověký štít", "Unknown", Era.MEDIEVAL_GOTHIC, Type.HISTORICAL_OBJECT, "Ozdobený bojový štít", "60cm", "YELLOW", groups.get(3), rooms.get(5)));
//        arts.add(createArt("Mona Lisa", "Leonardo da Vinci", Era.RENAISSANCE, Type.PAINTING, "Portrét ženy", "77x53cm", "YELLOW", groups.get(0), rooms.get(1)));
//        arts.add(createArt("David", "Michelangelo", Era.RENAISSANCE, Type.SCULPTURE, "Mramorová socha muže", "5.17m", "RED", groups.get(1), rooms.get(3)));
//        arts.add(createArt("Hvězdná noc", "Vincent van Gogh", Era.POST_IMPRESSIONISM, Type.PAINTING, "Slavná noční krajina", "74x92cm", "RED", groups.get(0), rooms.get(2)));
//        arts.add(createArt("Digitální vír", "Eva K.", Era.DIGITAL_ART, Type.DIGITAL_ART, "Interaktivní projekce", "projekce", "YELLOW", groups.get(2), rooms.get(4)));
//        arts.add(createArt("Faraonova maska", "Unknown", Era.ANCIENT_EGYPT, Type.HISTORICAL_OBJECT, "Zlatá pohřební maska", "50x30cm", "RED", groups.get(3), rooms.get(0)));
//        arts.add(createArt("Virtuální les", "Future.Art", Era.DIGITAL_ART, Type.INSTALLATION, "AR prostředí", "VR/AR", "YELLOW", groups.get(4), rooms.get(4)));
//        arts.add(createArt("Guernica", "Pablo Picasso", Era.CUBISM, Type.PAINTING, "Antiválečný obraz", "3.5x7.8m", "RED", groups.get(0), rooms.get(2)));
//        arts.add(createArt("Časová spirála", "DigitArt.cz", Era.DIGITAL_ART, Type.DIGITAL_ART, "Animace v loopu", "animace", "GREEN", groups.get(2), rooms.get(4)));
//        arts.add(createArt("The Thinker", "Auguste Rodin", Era.REALISM, Type.SCULPTURE, "Socha muže v přemýšlení", "186cm", "GREEN", groups.get(1), rooms.get(3)));
//        arts.add(createArt("Středověký štít", "Unknown", Era.MEDIEVAL_GOTHIC, Type.HISTORICAL_OBJECT, "Ozdobený bojový štít", "60cm", "YELLOW", groups.get(3), rooms.get(5)));
//
//        // ... (přidej dalších 10+ pokud chceš ještě víc)
//
//        artRepository.saveAll(arts);
//
//        // ===== EMERGENCY RECORDS =====
//        List<Art> emergencyArts = arts.subList(0, 5); // prvních 5 děl do nouze
//        List<User> employees = userRepository.findByRole(Role.ROLE_EMPLOYEE);
//        List<EmergencyRecord> emergencyRecords = new ArrayList<>();
//
//        for (int i = 0; i < emergencyArts.size(); i++) {
//            EmergencyRecord record = new EmergencyRecord();
//            record.setArt(emergencyArts.get(i));
//            record.setUser(employees.get(i % employees.size()));
//            record.setTimestamp(LocalDateTime.now().minusHours(i));
//            record.setNote("Testovací označení při startu.");
//            emergencyRecords.add(record);
//        }
//
//        emergencyRecordRepository.saveAll(emergencyRecords);
//    }
//
//    private User createUser(String email, String password, String firstName, String lastName, Role role,String phone) {
//        User user = role == Role.ROLE_ADMIN ? new Admin() : new Employee();
//        user.setEmail(email);
//        user.setPassword(passwordEncoder.encode(password));
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setRole(role);
//        user.setPhoneNumber(phone);
//        return user;
//    }
//
//    private Room createRoom(String name, int floor) {
//        Room room = new Room();
//        room.setName(name);
//        room.setFloorNumber(floor);
//        return room;
//    }
//
//    private Group createGroup(String name, String description) {
//        Group group = new Group();
//        group.setName(name);
//        group.setDescription(description);
//        return group;
//    }
//
//    private Art createArt(String name, String author, Era era, Type type, String description, String parameters, String color, Group group, Room room,String imagePath) {
//        Art art = new Art();
//        art.setName(name);
//        art.setAuthor(author);
//        art.setEra(era);
//        art.setType(type);
//        art.setDescription(description);
//        art.setParameters(parameters);
//        art.setColor(color);
//        art.setGroup(group);
//        art.setRoom(room);
//        art.setImgPath(imagePath);
//        return art;
//    }
//}
//


package cz.cvut.fel.muzeumSys;

import cz.cvut.fel.muzeumSys.model.*;
        import cz.cvut.fel.muzeumSys.model.enums.*;
        import cz.cvut.fel.muzeumSys.repository.*;
        import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@RequiredArgsConstructor
public class DBINIT implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final GroupRepository groupRepository;
    private final ArtRepository artRepository;
    private final EmergencyRecordRepository emergencyRecordRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) return;

        // ===== Obrázky ====
//        List<String> imagePaths = loadImagePaths("/arts");

        // ===== USERS =====
        List<User> users = new ArrayList<>();
        users.add(createUser("admin@muzeum.cz", "admin", "Admin", "Správce", Role.ROLE_ADMIN, "111111111"));
        users.add(createUser("vojta.kratina@gmail.com", "lol", "Admin", "Správce", Role.ROLE_ADMIN, "777888999"));
        users.add(createUser("employee@muzeum.cz", "employee", "Alice", "Kratka", Role.ROLE_EMPLOYEE, "777888998"));
        users.add(createUser("employee2@muzeum.cz", "pass2", "Bob", "Novák", Role.ROLE_EMPLOYEE, "777888997"));
        users.add(createUser("employee3@muzeum.cz", "pass3", "Clara", "Mašková", Role.ROLE_EMPLOYEE, "777888996"));
        users.add(createUser("employee4@muzeum.cz", "pass4", "David", "Liška", Role.ROLE_EMPLOYEE, "777888959"));
        users.add(createUser("employee5@muzeum.cz", "pass5", "Eva", "Zelená", Role.ROLE_EMPLOYEE, "7778889943"));
        userRepository.saveAll(users);

        // ===== ROOMS =====
        List<Room> rooms = List.of(
                createRoom("Starověk", 1),
                createRoom("Renesance", 2),
                createRoom("Moderní umění", 3),
                createRoom("Sochařský sál", 2),
                createRoom("Digitální galerie", 3),
                createRoom("Gotický výklenek", 1)
        );
        roomRepository.saveAll(rooms);

        // ===== GROUPS =====
        List<Group> groups = List.of(
                createGroup("Obrazy", "Výtvarné malby různých období"),
                createGroup("Sochy", "Kamenné, bronzové a hliněné sochy"),
                createGroup("Digitální umění", "Moderní tvorba s technologiemi"),
                createGroup("Historické objekty", "Artefakty z minulosti"),
                createGroup("Interaktivní instalace", "Multimediální zážitky")
        );
        groupRepository.saveAll(groups);

        // ===== ARTS =====
        List<Art> arts = new ArrayList<>();
        List<String> imagePaths = loadImagePaths();
        Random random = new Random();

        arts.add(createArt("Mona Lisa", "Leonardo da Vinci", Era.RENAISSANCE, Type.PAINTING, "Portrét ženy", "77x53cm", "YELLOW", groups.get(0), rooms.get(1), getRandomImage(imagePaths, random)));
        arts.add(createArt("David", "Michelangelo", Era.RENAISSANCE, Type.SCULPTURE, "Mramorová socha muže", "5.17m", "RED", groups.get(1), rooms.get(3), getRandomImage(imagePaths, random)));
        arts.add(createArt("Hvězdná noc", "Vincent van Gogh", Era.POST_IMPRESSIONISM, Type.PAINTING, "Slavná noční krajina", "74x92cm", "RED", groups.get(0), rooms.get(2), getRandomImage(imagePaths, random)));
        arts.add(createArt("Digitální vír", "Eva K.", Era.DIGITAL_ART, Type.DIGITAL_ART, "Interaktivní projekce", "projekce", "YELLOW", groups.get(2), rooms.get(4), getRandomImage(imagePaths, random)));
        arts.add(createArt("Faraonova maska", "Unknown", Era.ANCIENT_EGYPT, Type.HISTORICAL_OBJECT, "Zlatá pohřební maska", "50x30cm", "RED", groups.get(3), rooms.get(0), getRandomImage(imagePaths, random)));
        arts.add(createArt("Mona Lisa", "Leonardo da Vinci", Era.RENAISSANCE, Type.PAINTING, "Portrét ženy", "77x53cm", "YELLOW", groups.get(0), rooms.get(1), getRandomImage(imagePaths, random)));
        arts.add(createArt("David", "Michelangelo", Era.RENAISSANCE, Type.SCULPTURE, "Mramorová socha muže", "5.17m", "RED", groups.get(1), rooms.get(3), getRandomImage(imagePaths, random)));
        arts.add(createArt("Hvězdná noc", "Vincent van Gogh", Era.POST_IMPRESSIONISM, Type.PAINTING, "Slavná noční krajina", "74x92cm", "RED", groups.get(0), rooms.get(2), getRandomImage(imagePaths, random)));
        arts.add(createArt("Digitální vír", "Eva K.", Era.DIGITAL_ART, Type.DIGITAL_ART, "Interaktivní projekce", "projekce", "YELLOW", groups.get(2), rooms.get(4), getRandomImage(imagePaths, random)));
        arts.add(createArt("Faraonova maska", "Unknown", Era.ANCIENT_EGYPT, Type.HISTORICAL_OBJECT, "Zlatá pohřební maska", "50x30cm", "RED", groups.get(3), rooms.get(0), getRandomImage(imagePaths, random)));
        arts.add(createArt("Mona Lisa", "Leonardo da Vinci", Era.RENAISSANCE, Type.PAINTING, "Portrét ženy", "77x53cm", "YELLOW", groups.get(0), rooms.get(1), getRandomImage(imagePaths, random)));
        arts.add(createArt("David", "Michelangelo", Era.RENAISSANCE, Type.SCULPTURE, "Mramorová socha muže", "5.17m", "RED", groups.get(1), rooms.get(3), getRandomImage(imagePaths, random)));
        arts.add(createArt("Hvězdná noc", "Vincent van Gogh", Era.POST_IMPRESSIONISM, Type.PAINTING, "Slavná noční krajina", "74x92cm", "RED", groups.get(0), rooms.get(2), getRandomImage(imagePaths, random)));
        arts.add(createArt("Digitální vír", "Eva K.", Era.DIGITAL_ART, Type.DIGITAL_ART, "Interaktivní projekce", "projekce", "YELLOW", groups.get(2), rooms.get(4), getRandomImage(imagePaths, random)));
        arts.add(createArt("Faraonova maska", "Unknown", Era.ANCIENT_EGYPT, Type.HISTORICAL_OBJECT, "Zlatá pohřební maska", "50x30cm", "RED", groups.get(3), rooms.get(0), getRandomImage(imagePaths, random)));
        arts.add(createArt("Mona Lisa", "Leonardo da Vinci", Era.RENAISSANCE, Type.PAINTING, "Portrét ženy", "77x53cm", "YELLOW", groups.get(0), rooms.get(1), getRandomImage(imagePaths, random)));
        arts.add(createArt("David", "Michelangelo", Era.RENAISSANCE, Type.SCULPTURE, "Mramorová socha muže", "5.17m", "RED", groups.get(1), rooms.get(3), getRandomImage(imagePaths, random)));
        arts.add(createArt("Hvězdná noc", "Vincent van Gogh", Era.POST_IMPRESSIONISM, Type.PAINTING, "Slavná noční krajina", "74x92cm", "RED", groups.get(0), rooms.get(2), getRandomImage(imagePaths, random)));
        arts.add(createArt("Digitální vír", "Eva K.", Era.DIGITAL_ART, Type.DIGITAL_ART, "Interaktivní projekce", "projekce", "YELLOW", groups.get(2), rooms.get(4), getRandomImage(imagePaths, random)));
        arts.add(createArt("Faraonova maska", "Unknown", Era.ANCIENT_EGYPT, Type.HISTORICAL_OBJECT, "Zlatá pohřební maska", "50x30cm", "RED", groups.get(3), rooms.get(0), getRandomImage(imagePaths, random)));
        arts.add(createArt("Mona Lisa", "Leonardo da Vinci", Era.RENAISSANCE, Type.PAINTING, "Portrét ženy", "77x53cm", "YELLOW", groups.get(0), rooms.get(1), getRandomImage(imagePaths, random)));
        arts.add(createArt("David", "Michelangelo", Era.RENAISSANCE, Type.SCULPTURE, "Mramorová socha muže", "5.17m", "RED", groups.get(1), rooms.get(3), getRandomImage(imagePaths, random)));
        arts.add(createArt("Hvězdná noc", "Vincent van Gogh", Era.POST_IMPRESSIONISM, Type.PAINTING, "Slavná noční krajina", "74x92cm", "RED", groups.get(0), rooms.get(2), getRandomImage(imagePaths, random)));
        arts.add(createArt("Digitální vír", "Eva K.", Era.DIGITAL_ART, Type.DIGITAL_ART, "Interaktivní projekce", "projekce", "YELLOW", groups.get(2), rooms.get(4), getRandomImage(imagePaths, random)));
        arts.add(createArt("Faraonova maska", "Unknown", Era.ANCIENT_EGYPT, Type.HISTORICAL_OBJECT, "Zlatá pohřební maska", "50x30cm", "RED", groups.get(3), rooms.get(0), getRandomImage(imagePaths, random)));
        arts.add(createArt("Mona Lisa", "Leonardo da Vinci", Era.RENAISSANCE, Type.PAINTING, "Portrét ženy", "77x53cm", "YELLOW", groups.get(0), rooms.get(1), getRandomImage(imagePaths, random)));
        arts.add(createArt("David", "Michelangelo", Era.RENAISSANCE, Type.SCULPTURE, "Mramorová socha muže", "5.17m", "RED", groups.get(1), rooms.get(3), getRandomImage(imagePaths, random)));
        arts.add(createArt("Hvězdná noc", "Vincent van Gogh", Era.POST_IMPRESSIONISM, Type.PAINTING, "Slavná noční krajina", "74x92cm", "RED", groups.get(0), rooms.get(2), getRandomImage(imagePaths, random)));
        arts.add(createArt("Digitální vír", "Eva K.", Era.DIGITAL_ART, Type.DIGITAL_ART, "Interaktivní projekce", "projekce", "YELLOW", groups.get(2), rooms.get(4), getRandomImage(imagePaths, random)));
        arts.add(createArt("Faraonova maska", "Unknown", Era.ANCIENT_EGYPT, Type.HISTORICAL_OBJECT, "Zlatá pohřební maska", "50x30cm", "RED", groups.get(3), rooms.get(0), getRandomImage(imagePaths, random)));

        artRepository.saveAll(arts);

        // ===== EMERGENCY RECORDS =====
        List<Art> emergencyArts = arts.subList(0, 3);
        List<User> employees = userRepository.findByRole(Role.ROLE_EMPLOYEE);
        List<EmergencyRecord> emergencyRecords = new ArrayList<>();

        for (int i = 0; i < emergencyArts.size(); i++) {
            EmergencyRecord record = new EmergencyRecord();
            record.setArt(emergencyArts.get(i));
            record.setUser(employees.get(i % employees.size()));
            record.setTimestamp(LocalDateTime.now().minusHours(i));
            record.setNote("Testovací označení při startu.");
            emergencyRecords.add(record);
        }

        emergencyRecordRepository.saveAll(emergencyRecords);
    }

    private List<String> loadImagePaths() {
        return List.of(
                "/arts/dilo1.png",
                "/arts/Dilo2.png",
                "/arts/dilo3.jpeg",
                "/arts/dilo4.jpeg",
                "/arts/dilo5.jpeg",
                "/arts/dilo9.png",
                "/arts/dilo10.png"
        );
    }

    private String getRandomImage(Random random, List<String> images) {
        return images.isEmpty() ? "" : images.get(random.nextInt(images.size()));
    }

    private List<String> loadImagePaths(String folder) {
        File dir = new File(folder);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg"));
        List<String> paths = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                paths.add(folder + "/" + file.getName());
            }
        }
        return paths;
    }

    private String getRandomImage(List<String> images, Random random) {
        return images.isEmpty() ? "" : images.get(random.nextInt(images.size()));
    }

    private User createUser(String email, String password, String firstName, String lastName, Role role, String phone) {
        User user = role == Role.ROLE_ADMIN ? new Admin() : new Employee();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role);
        user.setPhoneNumber(phone);
        return user;
    }

    private Room createRoom(String name, int floor) {
        Room room = new Room();
        room.setName(name);
        room.setFloorNumber(floor);
        return room;
    }

    private Group createGroup(String name, String description) {
        Group group = new Group();
        group.setName(name);
        group.setDescription(description);
        return group;
    }

    private Art createArt(String name, String author, Era era, Type type, String description, String parameters, String color, Group group, Room room, String imagePath) {
        Art art = new Art();
        art.setName(name);
        art.setAuthor(author);
        art.setEra(era);
        art.setType(type);
        art.setDescription(description);
        art.setParameters(parameters);
        art.setColor(color);
        art.setGroup(group);
        art.setRoom(room);
        art.setImgPath(imagePath);
        return art;
    }
}

