package controlo;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import modelo.dao.ClienteDao;
import modelo.domain.Cliente;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author Evangelino
 */
public final class ClienteControlo {

    private final PropertyChangeSupport propertyChangeSupport
            = new PropertyChangeSupport(this);
    private Cliente clienteDigitado;
    private Cliente clienteSeleccionado;
    private List<Cliente> clientesTabela;
    private ClienteDao clienteDao;

    public ClienteControlo() {
        clienteDao = new ClienteDao();
        clientesTabela = ObservableCollections.observableList(new ArrayList<>());
        novo();
        pesquisar();
    }

    public void novo() {
        setClienteDigitado(new Cliente());
    }

    public void pesquisar() {
        clientesTabela.clear();
        clientesTabela.addAll(clienteDao.pesquisar(clienteDigitado));
    }

    public void salvra() {
        clienteDao.salvarAtualizar(clienteDigitado);
        novo();
        pesquisar();
    }

    public void excluir() {
        clienteDao.excluir(clienteDigitado);
        novo();
        pesquisar();
    }

    public Cliente getClienteDigitado() {
        return clienteDigitado;
    }

    public void setClienteDigitado(Cliente clienteDigitado) {
        Cliente oldClienteDigitado = this.clienteDigitado;
        this.clienteDigitado = clienteDigitado;
        propertyChangeSupport.firePropertyChange("clienteDigitado", oldClienteDigitado, clienteDigitado);
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
        if (this.clienteSeleccionado != null) {
            setClienteDigitado(clienteSeleccionado);

        }
    }

    public List<Cliente> getClientesTabela() {
        return clientesTabela;
    }

    public void setClientesTabela(List<Cliente> clientesTabela) {
        this.clientesTabela = clientesTabela;
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);

    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListene) {
        propertyChangeSupport.removePropertyChangeListener(propertyChangeListene);

    }
//    Erro ao conectar
}
