package nl.vpro.poel.controller.admin;

import nl.vpro.poel.domain.User;
import nl.vpro.poel.domain.UserGroup;
import nl.vpro.poel.dto.RankingEntry;
import nl.vpro.poel.service.RankingService;
import nl.vpro.poel.service.UserService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/admin/export")
public class ExportCSVController {

    private final UserService userService;

    private final RankingService rankingService;

    @Autowired
    public ExportCSVController(UserService userService, RankingService rankingService) {
        this.userService = userService;
        this.rankingService = rankingService;
    }

    @RequestMapping(method = RequestMethod.GET)
    String showExportIndex() {
        return "admin/export";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    void exportUsers(HttpServletResponse response) throws IOException {
        setHeadersForCSVDownload(response, String.format("poel-users-%s.csv", now()));
        CSVFormat format = CSVFormat.DEFAULT.withHeader("id", "username", "role", "realName", "gameName", "userGroupName");
        CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(), format);
        for (User user : userService.getAllUsers()) {
            UserGroup userGroup = user.getUserGroup();
            String userGroupName = userGroup != null ? userGroup.getName() : null;
            csvPrinter.printRecord(
                    user.getId(),
                    user.getUsername(),
                    user.getRole(),
                    user.getRealName(),
                    user.getGameName(),
                    userGroupName
            );
        }
        csvPrinter.close();
    }

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    void exportRanking(HttpServletResponse response) throws IOException {
        setHeadersForCSVDownload(response, String.format("poel-ranking-%s.csv", now()));
        CSVFormat format = CSVFormat.DEFAULT.withHeader("rank", "score", "user_id", "username", "role", "realName", "gameName", "userGroup");
        CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(), format);
        for (RankingEntry<User, Integer> rankingEntry : rankingService.getUserRanking()) {
            User user = rankingEntry.getSubject();
            UserGroup userGroup = user.getUserGroup();
            String userGroupName = userGroup != null ? userGroup.getName() : null;
            csvPrinter.printRecord(
                    rankingEntry.getRank(),
                    rankingEntry.getScore(),
                    user.getId(),
                    user.getUsername(),
                    user.getRealName(),
                    user.getGameName(),
                    userGroupName
            );
        }
        csvPrinter.close();
    }

    private String now() {
        return DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
    }

    private void setHeadersForCSVDownload(HttpServletResponse response, String filename) {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", filename));
    }
}
