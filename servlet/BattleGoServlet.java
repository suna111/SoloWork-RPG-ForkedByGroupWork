package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Fighter;
import beans.Hero;
import beans.Slime;
import beans.Wizard;


@WebServlet("/BattleGoServlet")
public class BattleGoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/battle.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//ラジオボタンで攻撃対象と攻撃内容を取得する
		int target = Integer.parseInt(request.getParameter("target"));
		int action = Integer.parseInt(request.getParameter("action"));

		//セッションスコープに保存した内容を取得する
		HttpSession session = request.getSession();
		Hero hero = (Hero) session.getAttribute("hero");
		Fighter fighter = (Fighter) session.getAttribute("fighter");
		Wizard wizard = (Wizard) session.getAttribute("wizard");

		Slime slimeA = (Slime) session.getAttribute("slimeA");
		Slime slimeB = (Slime) session.getAttribute("slimeB");
		Slime slimeC = (Slime) session.getAttribute("slimeC");

		//選択内容に合わせて行動を分岐させる
		switch(action) {
		case 1:
			switch(target) {
				case 1:
					hero.attack(slimeA);
					break;
				case 2:
					hero.attack(slimeB);
					break;
				case 3:
					hero.attack(slimeC);
					break;
			}
			slimeA.attack(hero);
			break;
		case 2:
			switch(target) {
			case 1:
				hero.aid(hero, slimeA);
				break;
			case 2:
				hero.aid(hero, slimeB);
				break;
			case 3:
				hero.aid(hero, slimeC);
				break;
		}
			slimeB.attack(hero);
			break;
		case 3:
			switch(target) {
				case 1:
					hero.thunder(slimeA);
					break;
				case 2:
					hero.thunder(slimeB);
					break;
				case 3:
					hero.thunder(slimeC);
					break;
			}
			slimeC.attack(hero);
			break;
		}

		//HPが0以下にならないようにする
		if(slimeA.getHp() < 0 ) {
			slimeA.setHp(0);
		}

		if(slimeB.getHp() < 0 ) {
			slimeB.setHp(0);
		}

		if(slimeC.getHp() < 0 ) {
			slimeC.setHp(0);
		}


		//すべてのモンスターのHPが0になったらフォワード
		if((slimeA.getHp() == 0) && (slimeB.getHp() == 0) && (slimeC.getHp() == 0)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/battle2.jsp");
			dispatcher.forward(request, response);
		}

		//フォワードして表示
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/battle.jsp");
		dispatcher.forward(request, response);
	}

}
