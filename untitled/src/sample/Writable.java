package sample;

import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * @author T.Whiter
 * @Date 2020/2/22 12:59
 * @Version 1.0
 */
public interface Writable {
    public void write(@NotNull File file);
}
