package org.expensive.layout.winds;

import org.springframework.stereotype.Component;

import java.awt.*;

public class PicsWin extends Window {

    public PicsWin(Frame owner) {
        super(owner);
    }

    public PicsWin(Window owner) {
        super(owner);
    }

    public PicsWin(Window owner, GraphicsConfiguration gc) {
        super(owner, gc);
    }
}
