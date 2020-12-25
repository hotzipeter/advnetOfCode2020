package hu.adventofcode2020;

import java.util.List;

public class Passport {
    boolean byr = false, iyr = false, eyr = false, hgt = false, hcl = false, ecl = false, pid = false, cid = false;

    Passport(List<String> lines) {


        for (String line : lines) {
            String[] fields = line.split(" ");
            for (String field : fields) {
                String fieldname = field.split(":")[0];
                String fieldValue = field.split(":")[1];
                switch (fieldname) {
                    case "byr":
                        if (validateByr(fieldValue)) {
                            byr = true;
                        }
                        break;
                    case "iyr":
                        if (validateIyr(fieldValue)) {
                            iyr = true;
                        }
                        break;
                    case "eyr":
                        if (validateEyr(fieldValue)) {
                            eyr = true;
                        }
                        break;
                    case "hgt":
                        if (validateHgt(fieldValue)) {
                            hgt = true;}
                        break;
                    case "hcl":
                        if (validateHcl(fieldValue)) {hcl = true;System.out.println("valid hcl : " + fieldValue);}
                        else {System.out.println("invalid hcl : " + fieldValue);}
                        break;
                    case "ecl":
                        if (validateEcl(fieldValue)) {
                            ecl = true;
                        }
                        break;
                    case "pid":
                        if (validatePid(fieldValue)) {
                            pid = true;
                        }
                        break;
                    case "cid":
                        cid = true;
                        break;
                }
            }
        }
    }

    public boolean isValid() {
        return byr && iyr && eyr && hgt && hcl && ecl && pid;
    }

    public String toString() {
        String s = "";
        if (!byr) s = s.concat("byr ");
        if (!iyr) s = s.concat("iyr ");
        if (!eyr) s = s.concat("eyr ");
        if (!hgt) s = s.concat("hgt ");
        if (!hcl) s = s.concat("hcl ");
        if (!ecl) s = s.concat("ecl ");
        if (!pid) s = s.concat("pid ");
        if (!cid) s = s.concat("cid ");
        return s;
    }

    private boolean validateByr(String s) {
        try {
            int year = Integer.parseInt(s);
            return year >= 1920 && year <= 2002;
        } catch (Exception e) {
            return false;
        }

    }

    private boolean validateEyr(String s) {
        try {
            int year = Integer.parseInt(s);
            return year >= 2020 && year <= 2030;
        } catch (Exception e) {
            return false;
        }

    }

    private boolean validateIyr(String s) {
        try {
            int year = Integer.parseInt(s);
            return year >= 2010 && year <= 2020;
        } catch (Exception e) {
            return false;
        }

    }

    private boolean validateHgt(String s) {
        String metric = s.substring(s.length() - 2);
        try {


            int height = Integer.parseInt(s.substring(0, s.length() - 2));
            if (metric.equals("cm")) {
                return height >= 150 && height <= 193;
            } else if (metric.equals("in")) {
                return height >= 59 && height <= 76;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private boolean validateHcl(String s) {
        char[] chars = s.toCharArray();
        if (chars[0] != '#') return false;
        if (s.length() != 7) return false;
        s = s.substring(1);
        return s.matches("^[0-9,a-f]*$");
    }

    private boolean validateEcl(String s) {
        switch (s) {
            case "amb":
            case "grn":
            case "blu":
            case "brn":
            case "gry":
            case "hzl":
            case "oth":
                return true;
            default:
                return false;
        }
    }

    private boolean validatePid(String s) {
        if (s.length() != 9) return false;
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
