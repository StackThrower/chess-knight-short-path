package com.chess.console;

import com.chess.board.Cell;
import com.chess.board.CellNotFound;
import com.chess.piece.KnightPiece;
import com.chess.piece.Piece;
import com.chess.piece.registry.IncorrectRegistrySettings;
import com.chess.piece.registry.PieceRegistry;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class ConsolePiecePositionReader {

    /**
     * This method
     * @param input can be represented as Ka3, Bh7
     * @return Piece object
     */
   public static Piece parseFromString(String input) throws IncorrectConsoleInput, IncorrectRegistrySettings {

       Optional<String> optionalInput = Optional.of(input);
       String params = optionalInput.orElseThrow(IncorrectConsoleInput::new);

       if(params.length() != 3) throw new IncorrectConsoleInput();

       String pieceId = params.substring(0,1);
       String pieceLabel = params.substring(1,2);
       byte pieceY = (byte) (Byte.parseByte(params.substring(2,3)) - 1);

       Piece piece = null;

       for(PieceRegistry pieceRegistry : PieceRegistry.values()) {
           try {
               Class<Piece> pieceClass = pieceRegistry.getPiece();
               String registeredPieceId = (String)pieceClass.getDeclaredField("PIECE_ID")
                       .get(pieceClass.newInstance());
               if(pieceId.equalsIgnoreCase(registeredPieceId)) {
                   Constructor<?> constructor = pieceClass.getConstructor(Cell.class);

                   piece = (Piece)constructor.newInstance(new Cell(pieceLabel, pieceY));
                   break;
               }

           } catch (NoSuchFieldException | IllegalAccessException | InstantiationException e) {
               throw new IncorrectRegistrySettings("Incorrect field: PIECE_ID");
           } catch (NoSuchMethodException e) {
               throw new IncorrectRegistrySettings("Incorrect constructor for Piece class");
           } catch (InvocationTargetException e) {
               throw new RuntimeException(e);
           } catch (CellNotFound e) {
               throw new IncorrectConsoleInput();
           }
       }

       Optional<Piece> optionalPiece = Optional.of(piece);
       return optionalPiece.orElseThrow(IncorrectConsoleInput::new);
   }

}
