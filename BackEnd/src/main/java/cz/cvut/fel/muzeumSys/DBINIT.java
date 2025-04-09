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
        admin.setEmail("admin@muzeum.cz");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setRole(Role.ROLE_ADMIN);
        admin.setFirstName("Admin");
        admin.setLastName("User");
        admin.setPhoneNumber("123456789");

        User employee = new Employee();
        employee.setEmail("employee@muzeum.cz");
        employee.setPassword(passwordEncoder.encode("employee123"));
        employee.setRole(Role.ROLE_EMPLOYEE);
        employee.setFirstName("Employee");
        employee.setLastName("User");
        employee.setPhoneNumber("987654321");

        userRepository.saveAll(List.of(admin, employee));

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
        art1.setColor("WHITE");         // ✅ jako String
        art1.setDescription("A famous Greek statue.");
        art1.setParameters("203 cm, Marble");
        art1.setQrCode("VENUS_QR");
        art1.setGroup(group1);
        art1.setRoom(room1);

        Art art2 = new Art();
        art2.setName("Starry Night");
        art2.setEra(Era.POST_IMPRESSIONISM); // ✅ validní enum
        art2.setType(Type.PAINTING);         // ✅ validní enum
        art2.setAuthor("Vincent van Gogh");
        art2.setColor("BLUE/YELLOW");
        art2.setDescription("Famous post-impressionist painting.");
        art2.setParameters("74 cm × 92 cm");
        art2.setQrCode("STARRY_QR");
        art2.setGroup(group2);
        art2.setRoom(room2);

        artRepository.saveAll(List.of(art1, art2));

        // QR Codes
        QRCode qr1 = new QRCode();
        qr1.setArtId(art1.getId());
        qr1.setQrCode("VENUS_QR");
        qr1.setColor(Color.YELLOW); // ✅ použitelné barvy: GREEN, YELLOW, RED


        QRCode qr2 = new QRCode();
        qr2.setArtId(art2.getId());
        qr2.setQrCode("STARRY_QR");
        qr2.setColor(Color.RED); // např. RED pro kontrast

        qrCodeRepository.saveAll(List.of(qr1, qr2));
    }
}
