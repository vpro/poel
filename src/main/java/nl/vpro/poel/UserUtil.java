package nl.vpro.poel;

import lombok.experimental.UtilityClass;
import nl.vpro.poel.domain.CurrentUser;
import org.springframework.security.cas.authentication.CasAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.Optional;

@UtilityClass
public class UserUtil {

    public Optional<CurrentUser> getCurrentUser(Principal principal) {
        if (principal instanceof CasAuthenticationToken) {
            UserDetails userDetails = ((CasAuthenticationToken)principal).getUserDetails();
            if (userDetails instanceof CurrentUser) {
                return Optional.of((CurrentUser) userDetails);
            }
        }
        return Optional.empty();
    }
}
