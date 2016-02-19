package com.teci.gereteci.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teci.gereteci.model.*;
import com.teci.gereteci.model.Computador.Computador;

public interface Computadores extends JpaRepository<Computador, Integer>
{

}
