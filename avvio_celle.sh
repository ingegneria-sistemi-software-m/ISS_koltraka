#!/bin/bash

# Cambia directory
cd ./deployed/conwaycellsqak-1.0/bin || exit 1

# Salva il TTY attuale
TTY=$(tty)
TTY=${TTY#/dev/}  # Rimuove il prefisso /dev/

# Ciclo da 1 a 9
for i in {1..9}; do
    echo "Avvio N=$i..."
    # Avvia in una nuova finestra terminale (se disponibile) o in background
    ./conwaycellsqak &
done

# Aspetta che l'utente voglia terminare i processi
read -p "Premi [INVIO] per terminare i processi associati al terminale ($TTY)..."

echo "Terminazione processi..."
# Lista e termina i processi associati a questo TTY (escludendo bash)
ps -t "$TTY" h | grep -v bash | awk '{print $1}' | xargs kill
echo "Processi terminati."