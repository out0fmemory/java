package com.pubnub.api;

import com.pubnub.domain.Result;

import java.util.Enumeration;
import java.util.Hashtable;

public class HttpRequest {
    private volatile ResponseHandler responseHandler;
    private Hashtable headers;
    private String[] urlComponents;
    private Hashtable params;
    private String url;
    private boolean dar;
    private boolean subzero;
    private Worker _worker;
    private Result result;

    public Result getResult() {
		return result;
	}
	public void setResult(Result result) {

		this.result = result;
	}

    public boolean isSubzero() {
        return subzero;
    }

    public void setWorker(Worker worker) {
        this._worker = worker;
    }

    public Worker getWorker() {
        return this._worker;
    }

    public void setSubzero(boolean subzero) {
        this.subzero = subzero;
    }

    public boolean isDar() {
        return dar;
    }

    public void setDar(boolean dar) {
        this.dar = dar;
    }


    public HttpRequest(String[] urlComponents, Hashtable params,
            ResponseHandler rh, Result result) {
		this.setUrlComponents(urlComponents);
		this.setParams(params);
		this.setResponseHandler(rh);
		this.setResult(result);
    }
    
    public HttpRequest(String[] urlComponents, ResponseHandler rh, Result result) {
        this.setUrlComponents(urlComponents);
        this.setResponseHandler(rh);
        this.setResult(result);
    }

    public String[] getUrlComponents() {
        return urlComponents;
    }

    public void setUrlComponents(String[] urlComponents) {
        this.urlComponents = urlComponents;
    }

    public Hashtable getParams() {
        return params;
    }

    public void setParams(Hashtable params) {
        this.params = params;
    }

    public ResponseHandler getResponseHandler() {
        return responseHandler;
    }

    public void setResponseHandler(ResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
    }

    public Hashtable getHeaders() {
        return headers;
    }

    public void setHeaders(Hashtable headers) {
        this.headers = headers;
    }

    public String getUrl() {

        if (url != null) {
            return url;
        }

        String url = PubnubUtil.joinString(urlComponents, "/");

        if (this.params != null && this.params.size() > 0) {
            StringBuffer sb = new StringBuffer();
            sb.append(url).append("?");

            Enumeration paramsKeys = this.params.keys();
            boolean first = true;
            while (paramsKeys.hasMoreElements()) {
                if (!first) {
                    sb.append("&");
                } else
                    first = false;

                String key = (String) paramsKeys.nextElement();
                sb.append(PubnubUtil.urlEncode((String) key)).append("=")
                        .append(PubnubUtil.urlEncode((String) this.params.get(key)));
            }

            url = sb.toString();
        }
        this.url = url;

        return this.url;
    }
}