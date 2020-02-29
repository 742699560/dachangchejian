package com.dccj.servlet;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by zhaotao on 2016/4/27.
 */
public class RetailServletInputStream extends ServletInputStream {

    private byte[] bytes;
    private InputStream m_InputStream;


    public RetailServletInputStream(byte[] bytes) throws IOException {
        this.bytes = bytes;
        m_InputStream = null;
    }


    private InputStream acquireInputStream() throws IOException {
        if (m_InputStream == null) {
            m_InputStream = new ByteArrayInputStream(bytes);
        }

        return m_InputStream;
    }


    public void close() throws IOException {
        try {
            if (m_InputStream != null) {
                m_InputStream.close();
            }
        } catch (IOException e) {
            throw e;
        } finally {
            m_InputStream = null;
        }
    }


    public int read() throws IOException {
        return acquireInputStream().read();
    }


    public boolean markSupported() {
        return false;
    }


    public synchronized void mark(int i) {
        throw new UnsupportedOperationException("mark not supported");
    }


    public synchronized void reset() throws IOException {
        throw new IOException(new UnsupportedOperationException("reset not supported"));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setReadListener(ReadListener readListener) {

    }
}
