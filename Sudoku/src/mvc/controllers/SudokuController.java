package mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SudokuController {

	@RequestMapping("/sudoku")
	public ModelAndView sudoku(){
		
		return new ModelAndView();
	}
}
