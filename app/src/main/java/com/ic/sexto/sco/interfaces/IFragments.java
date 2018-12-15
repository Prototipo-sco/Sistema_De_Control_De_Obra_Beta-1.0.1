package com.ic.sexto.sco.interfaces;

import com.ic.sexto.sco.fragments.BienvenidaFragment;
import com.ic.sexto.sco.fragments.ConsultaListaUsuarioImagenUrlFragment;
import com.ic.sexto.sco.fragments.ConsultaListaUsuarioImagenesGaleriaUrlFragment;
import com.ic.sexto.sco.fragments.ConsultaUsuarioUrlFragment;
import com.ic.sexto.sco.fragments.RegistrarGaleriaFragment;
import com.ic.sexto.sco.fragments.RegistrarUsuarioFragment;

public interface IFragments extends BienvenidaFragment.OnFragmentInteractionListener,
                                    RegistrarUsuarioFragment.OnFragmentInteractionListener,
                                    ConsultaUsuarioUrlFragment.OnFragmentInteractionListener,
                                    ConsultaListaUsuarioImagenUrlFragment.OnFragmentInteractionListener,
                                    RegistrarGaleriaFragment.OnFragmentInteractionListener,
                                    ConsultaListaUsuarioImagenesGaleriaUrlFragment.OnFragmentInteractionListener{
}
