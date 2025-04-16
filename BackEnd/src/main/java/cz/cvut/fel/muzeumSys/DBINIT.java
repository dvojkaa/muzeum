package cz.cvut.fel.muzeumSys;

import cz.cvut.fel.muzeumSys.model.*;
import cz.cvut.fel.muzeumSys.model.enums.Color;
import cz.cvut.fel.muzeumSys.model.enums.Era;
import cz.cvut.fel.muzeumSys.model.enums.Role;
import cz.cvut.fel.muzeumSys.model.enums.Type;
import cz.cvut.fel.muzeumSys.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DBINIT implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final GroupRepository groupRepository;
    private final ArtRepository artRepository;
    private final QRCodeRepository qrCodeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) return; // Pokud už data existují, neprovádět znovu

        // Users
        User admin = new Admin();
        admin.setEmail("vojta.kratina@gmail.com");
        admin.setPassword(passwordEncoder.encode("lol"));
        admin.setRole(Role.ROLE_ADMIN);
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setPhoneNumber("123456789");

        User employee = new Employee();
        employee.setEmail("employee@muzeum.cz");
        employee.setPassword(passwordEncoder.encode("lol"));
        employee.setRole(Role.ROLE_EMPLOYEE);
        employee.setFirstName("Employee");
        employee.setLastName("User");
        employee.setPhoneNumber("987654321");

        User employee1 = new Employee();
        employee1.setEmail("s.chlustinova@muzum.cz");
        employee1.setPassword(passwordEncoder.encode("sara"));
        employee1.setRole(Role.ROLE_EMPLOYEE);
        employee1.setFirstName("Sara");
        employee1.setLastName("Chlustinka");
        employee1.setPhoneNumber("567372937");

        userRepository.saveAll(List.of(admin, employee, employee1));

        // Rooms
        Room room1 = new Room();
        room1.setName("Ancient Room");
        room1.setFloorNumber(1);

        Room room2 = new Room();
        room2.setName("Modern Room");
        room2.setFloorNumber(2);

        roomRepository.saveAll(List.of(room1, room2));

        // Groups
        Group group1 = new Group();
        group1.setName("Sculptures");
        group1.setDescription("Stone and metal sculptures");

        Group group2 = new Group();
        group2.setName("Paintings");
        group2.setDescription("Oil and watercolor paintings");

        groupRepository.saveAll(List.of(group1, group2));

        // Arts
        Art art1 = new Art();
        art1.setName("Venus de Milo");
        art1.setEra(Era.ANCIENT_GREECE); // ✅ validní enum
        art1.setType(Type.SCULPTURE);   // ✅ validní enum
        art1.setAuthor("Unknown");
        art1.setColor("RED");         // ✅ jako String
        art1.setDescription("A famous Greek statue.");
        art1.setParameters("203 cm, Marble");
        art1.setGroup(group1);
        art1.setRoom(room1);

        Art art2 = new Art();
        art2.setName("Starry Night");
        art2.setEra(Era.POST_IMPRESSIONISM); // ✅ validní enum
        art2.setType(Type.PAINTING);         // ✅ validní enum
        art2.setAuthor("Vincent van Gogh");
        art2.setColor("YELLOW");
        art2.setDescription("Famous post-impressionist painting.");
        art2.setParameters("74 cm × 92 cm");
        art2.setGroup(group2);
        art2.setRoom(room2);

        Art art3 = new Art();
        art3.setName("The Thinker");
        art3.setEra(Era.POST_IMPRESSIONISM);
        art3.setType(Type.SCULPTURE);
        art3.setAuthor("Auguste Rodin");
        art3.setColor("GREEN");
        art3.setDescription("A sculpture of a man in deep thought.");
        art3.setParameters("Height: 186 cm");
        art3.setGroup(group1);
        art3.setRoom(room1);

        Art art4 = new Art();
        art4.setName("The Starry Night");
        art4.setEra(Era.CUBISM);
        art4.setType(Type.PAINTING);
        art4.setAuthor("Vincent van Gogh");
        art4.setColor("YELLOW");
        art4.setDescription("A famous depiction of a night sky.");
        art4.setParameters("73.7 cm × 92.1 cm, Oil on canvas");
        art4.setGroup(group1);
        art4.setRoom(room1);

        Art art5 = new Art();
        art5.setName("Terracotta Army Soldier");
        art5.setEra(Era.MEDIEVAL_GOTHIC);
        art5.setType(Type.SCULPTURE);
        art5.setAuthor("Unknown");
        art5.setColor("RED");
        art5.setDescription("One of the many clay soldiers from the Terracotta Army.");
        art5.setParameters("Life-size, Clay");
        art5.setGroup(group1);
        art5.setRoom(room1);


        artRepository.saveAll(List.of(art1, art2,art3,art4,art5));
    }
}
