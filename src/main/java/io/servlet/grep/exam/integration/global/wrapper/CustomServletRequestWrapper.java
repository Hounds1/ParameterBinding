package io.servlet.grep.exam.integration.global.wrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @deprecated
 * <br>
 * <strong style="color:red">
 * Notice
 * </strong>
 * <br>
 * <span style="color:#4CC7CF;">
 * The RequestWrapper is not always needed.
 * Typically, the ServletRequest pipeline should store attributes using their own technical capabilities.
 * Therefore, unless you are in a special case, you do not need to use this.
 * </span>
 */

@Deprecated
public class CustomServletRequestWrapper extends HttpServletRequestWrapper {

    private final Map<String, String[]> parameters;

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public CustomServletRequestWrapper(HttpServletRequest request) {
        super(request);
        this.parameters = new HashMap<>(request.getParameterMap());
    }

    public void addParam(String name, String value) {
        parameters.put(name, new String[] {value});
    }

    @Override
    public String getParameter(String name) {
        String[] values = parameters.get(name);

        if (values != null && values.length > 0) {
            return values[0];
        }

        return super.getParameter(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return parameters;
    }

    @Override
    public String[] getParameterValues(String name) {
        return parameters.get(name);
    }
}
