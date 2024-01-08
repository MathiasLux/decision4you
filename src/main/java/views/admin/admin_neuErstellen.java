package views.admin;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("") // map view to the root
class admin_neuErstellen extends VerticalLayout {
    admin_neuErstellen() {
        add(new H1("Hello, world"));
    }
}
