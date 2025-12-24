@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public User register(UserRegisterDto dto) {
        // Check if email exists [cite: 81, 214]
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        // Create User entity
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword())) // Encode password [cite: 215]
                .createdAt(LocalDateTime.now())
                .roles(Collections.singleton(Role.ROLE_USER)) // Default role [cite: 215]
                .build();

        return userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {
        // Authenticate user (simplified) [cite: 216]
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found")); // [cite: 218]

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
             throw new BadCredentialsException("Invalid password");
        }

        // Generate JWT [cite: 217]
        String token = jwtProvider.generateToken(user.getId(), user.getEmail(), user.getRoles());

        return new AuthResponse(token, user.getId(), user.getEmail(), 
                user.getRoles().stream().map(Enum::name).collect(Collectors.toSet()));
    }
}