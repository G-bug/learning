package client;

import context.Context;
import state.CancellationState;
import state.RegisteredState;

public class Client {

    public static void main(String[] args) {

        Context context = new Context();

        System.out.println("初始为 cancellation");

        context.setUserState(new CancellationState());

        context.registered().online().cancellation();

        System.out.println("\n初始为 registered");

        context.setUserState(new RegisteredState());

        context.cancellation().offline().online();
    }

}
