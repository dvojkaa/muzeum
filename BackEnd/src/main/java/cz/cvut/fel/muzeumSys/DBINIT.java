package cz.cvut.fel.muzeumSys;

import cz.cvut.fel.muzeumSys.model.*;
import cz.cvut.fel.muzeumSys.model.enums.Era;
import cz.cvut.fel.muzeumSys.model.enums.Role;
import cz.cvut.fel.muzeumSys.model.enums.Type;
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
                "dilo1.png",
                "Dilo2.png",
                "dilo3.jpeg",
                "dilo4.jpeg",
                "dilo5.jpeg",
                "dilo9.png",
                "dilo10.png"
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

