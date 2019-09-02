package nadiatests;

import org.apache.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JaxbUtil {
    private static Logger LOG = Logger.getLogger(JaxbUtil.class);
    private static JaxbUtil instance = null;
    private static HashMap<String, JAXBContext> contextMap = new HashMap<String, JAXBContext>();

    private JaxbUtil() {}

    /**
     * returns an instance
     *
     * @return
     * @throws javax.xml.bind.JAXBException
     */
    public static JaxbUtil getInstance() throws JAXBException {
        if (instance == null) {
            instance = new JaxbUtil();
        }
        return instance;
    }

    /**
     * Initialize JaxbUtil to save processing time
     * send a list of classes to initialize map of unmarshaller
     *
     * @param classesList
     * @throws JAXBException
     */
    public static void init(List<Class> classesList) throws JAXBException {
        JAXBContext context = null;
        try {
            if (instance == null) {
                instance = new JaxbUtil();
            }
            for (Class cls : classesList) {
                context = JAXBContext.newInstance(cls);
                contextMap.put(cls.getName(), context);
            }
        } catch (JAXBException e) {
            String message = String.format("Method:%s Error:%s", "initializeContext", e.getMessage());
            LOG.error(message, e);
            throw e;
        }
    }

    /**
     * Read XML from the input stream and convert into an object of the expected
     * class. If the input stream is null, it returns null with no exception.
     *
     * @param cls expected class
     * @param inStream XML input stream
     * @return a java object
     * @throws JAXBException
     */
    public Object convertToObject(Class cls, InputStreamReader inStream) throws JAXBException {
        if (inStream == null) {
            return null;
        }
        JAXBContext context = contextMap.get(cls.getName());
        if (context == null) {
            context = JAXBContext.newInstance(cls);
            contextMap.put(cls.getName(), context);
        }
        Unmarshaller unMarshaller = context.createUnmarshaller();
        if (unMarshaller == null) {
            List<Class> ls = new ArrayList<Class>();
            ls.add(cls);
            init(ls);
        }
        Object o = unMarshaller.unmarshal(inStream);
        return o;
    }

    /**
     * convert object to xml
     *
     * @param o
     * @return
     * @throws JAXBException
     */
    public String convertToXML(Object o) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(o.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        ByteArrayOutputStream sos = new ByteArrayOutputStream();
        m.marshal(o, sos);
        return sos.toString();

    }
}

