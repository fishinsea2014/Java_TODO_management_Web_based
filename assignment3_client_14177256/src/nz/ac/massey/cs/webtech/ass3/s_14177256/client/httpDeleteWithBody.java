/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.massey.cs.webtech.ass3.s_14177256.client;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import java.net.URI;
import org.apache.http.annotation.NotThreadSafe;

/**
 *
 * @author QSG
 * This method is to help send body when sending a delete request
 */
@NotThreadSafe
public class httpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";
    public String getMethod() { return METHOD_NAME; }

    public httpDeleteWithBody(final String uri) {
        super();
        setURI(URI.create(uri));
    }
    public httpDeleteWithBody(final URI uri) {
        super();
        setURI(uri);
    }
    public httpDeleteWithBody() { super(); }
}
