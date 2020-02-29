package com.dccj.servlet;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author he peng
 * @date 2018/10/1
 */
public class BodyCachingHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private ResponsePrintWriter writer;
    private MyServletOutputStream out;

    public BodyCachingHttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    /*@Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new MyServletOutputStream(bytes); // 将数据写到 byte 中
    }*/

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        //一定要先判断当前out为空才能去新建out对象，否则一次请求会出现多个out对象
        if (out == null) {
            out = new MyServletOutputStream(super.getOutputStream());
        }
        return out;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        //一定要先判断当前writer为空才能去新建writer对象，否则一次请求会出现多个writer对象
        if (writer == null) {
            writer = new ResponsePrintWriter(super.getWriter());
        }
        return writer;
    }

    public ResponsePrintWriter getMyWriter() {
        return writer;
    }

    public MyServletOutputStream getMyOutputStream() {
        return out;
    }
    /**
     * 重写父类的 getWriter() 方法，将响应数据缓存在 PrintWriter 中
     */
   /* @Override
    public PrintWriter getWriter() throws IOException {
        try {
            pwrite = new PrintWriter(new OutputStreamWriter(bytes, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return pwrite;
    }*/

    /**
     * 获取缓存在 PrintWriter 中的响应数据
     *
     * @return
     */
    public String getBody() {
        String ret = "";
        if (null != writer) {
            ret = writer.getContent();
            writer.myFlush();
        }
        if (null != out) {
            ret = out.getBuffer();
            out.myFlush();
        }
        return ret;
    }

    public class MyServletOutputStream extends ServletOutputStream {
        private ServletOutputStream outputStream;
        private StringBuffer buffer;

        public MyServletOutputStream(ServletOutputStream outputStream) {
            this.outputStream = outputStream;
            buffer = new StringBuffer();
        }

        @Override
        public void write(int b) throws IOException {
            outputStream.write(b);
        }

        @Override
        public void write(byte[] b, int off, int len) throws IOException {
            outputStream.write(b, off, len);
            byte[] bytes = new byte[len];
            System.arraycopy(b, off, bytes, 0, len);
            buffer.append(new String(bytes, "UTF-8"));
        }

        @Override
        public void write(byte[] b) throws IOException {
            outputStream.write(b);
            buffer.append(new String(b));
        }

        @Override
        public void flush() throws IOException {
            super.flush();
        }

        //清空buffer，以便下一次重新使用
        public void myFlush() {
            outputStream = null;
            buffer = null;
        }

        public String getBuffer() {
            if (buffer != null) {
                return buffer.toString();
            }
            return null;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setWriteListener(WriteListener writeListener) {

        }
    }

    public class ResponsePrintWriter extends PrintWriter {
        private StringBuffer buffer;

        public ResponsePrintWriter(PrintWriter out) {
            super(out);
            buffer = new StringBuffer();
        }

        public String getContent() {
            return buffer == null ? null : buffer.toString();
        }

        @Override
        public void flush() {
            super.flush();
        }

        //清空buffer，以便下一次重新使用
        public void myFlush() {
            buffer = null;
        }

        @Override
        public void write(char[] buf, int off, int len) {
            super.write(buf, off, len);
            char[] destination = new char[len];
            System.arraycopy(buf, off, destination, 0, len);
            buffer.append(destination);
        }

        @Override
        public void write(String s) {
            super.write(s);
            buffer.append(s);
        }
    }
}