@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterDto {
    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
}
