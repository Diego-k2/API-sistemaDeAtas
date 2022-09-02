package br.com.ApiSistemaDeAtas.util;

public class VerificadorCpf {

    public static boolean isCpfValid(String cpf){

        String formatCpf = cpf.replaceAll("[^0-9]+" ,"");

        if(formatCpf.length() == 11){
            if(cpf.equals(verificaDigitos(formatCpf))){
                return true;
            }
            return false;
        }

        return false;
    }

    private static String verificaDigitos(String cpf){

        int j = 10;
        int soma = 0;
        String cpfVerif = "";

        for(int i = 0; i < 9; i++){
            cpfVerif += cpf.charAt(i);
            Integer n = Integer.parseInt(String.valueOf(cpf.charAt(i)));
            soma += n * j;
            j--;
        }

        int restoDivisao = soma % 11;
        Integer verifPrimeiroDigito = 11 - restoDivisao;

        if(verifPrimeiroDigito >= 10){
            return cpfVerif + 0;
        }

        return verificaSegundogito(cpfVerif + verifPrimeiroDigito);
    }

    private static String verificaSegundogito(String cpf){


        int j = 11;
        int soma = 0;
        String cpfVerif = "";

        for(int i = 0; i < 10; i++){
            cpfVerif += cpf.charAt(i);
            Integer n = Integer.parseInt(String.valueOf(cpf.charAt(i)));
            soma += n * j;
            j--;
        }

        int restoDivisao = soma % 11;
        Integer verifSegundoDigito = 11 - restoDivisao;

        if(verifSegundoDigito >= 10){
            return cpfVerif + 0;
        }

        return cpfVerif + verifSegundoDigito.toString();
    }

}
