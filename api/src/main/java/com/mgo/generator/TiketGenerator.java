package com.mgo.generator;

import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

import com.mgo.entity.Booking;
import com.mgo.entity.BookingLine;
import com.mgo.entity.City;
import com.mgo.entity.OrganizationConfig;
import com.mgo.util.BarCodes;
import com.mgo.util.Strings;

public class TiketGenerator {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/YYYY");

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd MMMM YYYY");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        // mercredi 01 janv. 2020

        private Generator generator = new Generator(300, 600);

        public byte[] generate(Booking booking,
                               BookingLine line,
                               OrganizationConfig companyconfig) throws IOException {
                String name =line.getDeparture().getOrganization().getName();
                String logoUrl = line.getDeparture().getOrganization().getTicketLogo();

                String address =line.getDeparture().getOrganization().getAddress() != null
                                                ? line.getDeparture().getOrganization().getAddress()
                                                : null;
                City city = line.getDeparture().getFrom();

                String phone = null /*line
                                ? booking.getDeparture().getCompanyagency().getPhone()
                                : null*/;

                generator.start();

                if (logoUrl != null) {
                        generator.imageFromUrl(logoUrl)
                                        .verticalSpacing(20);
                } else if (name != null) {
                        generator
                                        .style(new Style().setAlignment(Alignment.CENTER)
                                                        .setFont(new Font("default", Font.BOLD, 16)))
                                        .text(name)
                                        .verticalSpacing(20)
                                        .resetStyle();
                }

                if (!Strings.isEmptyOrNull(address)) {
                        generator.text(address);
                }
                generator.text(city.getName() + " " + city.getCityId());

                if (!Strings.isEmptyOrNull(phone)) {
                        generator.text("Tél. n° " + phone)
                                        .verticalSpacing(2);

                }

                generator.style(new Style().setFont(new Font("default", Font.PLAIN, 10)))
                                .text("Date du voyage :")
                                .verticalSpacing(10)
                                .style(new Style().setAlignment(Alignment.CENTER)
                                                .setFont(new Font("default", Font.BOLD, 16))
                                                .setUnderlined(true))
                                .text(dateFormat.format(line.getDeparture().getDateTime())) // "mercredi 01 janv.
                                                                                                // 2020"
                                .verticalSpacing(4)
                                .text("à " + timeFormat.format(line.getDeparture().getDateTime()))
                                .verticalSpacing(8)
                                .resetStyle()
                                .style(new Style().setAlignment(Alignment.LEFT))
                                .text("Départ : " + line.getDeparture().getFrom().getName())
                                .text("Arrivé : " + line.getDeparture().getTo().getName())
                                .verticalSpacing(8)
                                .style(new Style().setUnderlined(true))
                                .text("CATEGORIE " + line.getDeparture().getCategory().getName())
                                .resetStyle()
                                .verticalSpacing(8)
                                .style(new Style().setAlignment(Alignment.LEFT))
                                .text("Client:      " + booking.getClient().getFirstName())
                                .text("Tél:          " + booking.getClient().getPhoneNumber())
                                .text("Place n°:  " + line.getSeats().stream()
                                                .map(bp -> bp.getSeatNumber().toString())
                                                .collect(Collectors.joining("-")))
                                .verticalSpacing(8)
                                .style(new Style().setUnderlined(true))
                                .verticalSpacing(8)
                                .text("PAIMENT")
                                .verticalSpacing(8)
                                .resetStyle()
                                .disableNextNewLine()
                                .style(new Style().setAlignment(Alignment.LEFT))
                                .text("Nombre de places :")
                                .style(new Style().setAlignment(Alignment.RIGHT))
                                .enableNextNewLine()
                                .text(line.getSeats().size() + "")
                                .disableNextNewLine()
                                .style(new Style().setAlignment(Alignment.LEFT))
                                .text("Total à payer : ")
                                .enableNextNewLine()
                                .style(new Style().setAlignment(Alignment.RIGHT))
                                .text("Ar " + booking.getAmount())
                                .disableNextNewLine()
                                .style(new Style().setAlignment(Alignment.LEFT))
                                .text(booking.getPaymentMethod())
                                .enableNextNewLine()
                                .style(new Style().setAlignment(Alignment.RIGHT))
                                .text("Ar " + booking.getAmount())
                                .disableNextNewLine()
                                .style(new Style().setAlignment(Alignment.LEFT))
                                .text("Reste à payer : ")
                                .enableNextNewLine()
                                .style(new Style().setAlignment(Alignment.RIGHT))
                                .text("TOUT PAYE")
                                .verticalSpacing(8)
                                .style(new Style().setUnderlined(true).setAlignment(Alignment.CENTER))
                                .text("HAFATRA")
                                .style(new Style().setAlignment(Alignment.LEFT).setUnderlined(false))
                                .verticalSpacing(8)
                                .text("- Date de réservation : " + simpleDateFormat.format(booking.getDate()) + " "
                                                + timeFormat.format(booking.getDate()))
                                .text("- Ny billet efa lafo dia TSY MIVERINA INTSONY raha misy FAHATARAM-POTOANA")
                                .style(new Style().setAlignment(Alignment.CENTER)
                                                .setVerticalAlignment(VerticalAlignment.BOTTOM))
                                .text("M-Go");
                ;

                return BarCodes.bufferedImage2ByteArray(generator.end(), "png");

        }

}
