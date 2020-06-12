package com.demo.shaadi.user;

import com.demo.shaadi.holder.ResultHolder;

public interface UserClickListener {
    void accept(ResultHolder holder);
    void decline(ResultHolder holder);
}
