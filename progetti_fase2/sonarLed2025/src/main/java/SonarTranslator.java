package main.java;

import it.unibo.kactor.ActorBasic;
import unibo.basicomm23.interfaces.IApplMessage;
import unibo.basicomm23.utils.CommUtils;

public class SonarTranslator {
	protected ActorBasic owner;

	public SonarTranslator(ActorBasic owner) {
		this.owner = owner;
	}

	public void cvtToApplMessage(String msg) {
		CommUtils.outyellow("cvtToApplMessage " +  owner.getName() + " " + msg);
		
		IApplMessage applMsg = CommUtils.buildDispatch(owner.getName(), "misurazione_disp", msg, owner.getName());
		owner.sendMsgToMyself(applMsg);
	}

}
