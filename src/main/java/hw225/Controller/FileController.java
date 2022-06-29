package hw225.Controller;

import hw225.Service.IFileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

@Controller
public class FileController {
    @Autowired
    private IFileService fileService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("path", "");
        model.addAttribute("listFile", fileService.getRoots());
        return "index";
    }

    @PostMapping("/input")
    public String input(String path, Model model) {
        model.addAttribute("path", path);
        model.addAttribute("listFile", fileService.getInfo(Path.of(path)));
        return "index";
    }

    @PostMapping("/next")
    public String next(String path, Model model, String name) {
        Path newPath;

        if (Objects.equals(path, ""))
            newPath = Path.of(path + name);
        else
            newPath = Path.of(path + File.separator + name);

        model.addAttribute("path", newPath);
        model.addAttribute("listFile", fileService.getInfo(newPath));
        return "index";
    }

    @PostMapping("/back")
    public String back(String path, Model model) {
        Path newPath = Path.of(path).getParent();

        if (newPath == null) return "redirect:/";

        model.addAttribute("path", newPath);
        model.addAttribute("listFile", fileService.getInfo(newPath));
        return "index";
    }
}